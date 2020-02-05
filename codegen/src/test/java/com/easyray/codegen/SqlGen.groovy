package com.easyray.codegen
/**
 * @Date: 20-2-4
 * @Author: wyy
 */
class SqlGen {

    private String sqlPath = ""

    private List<Entity> entityList


    private static final String _SQL_CREATE_TABLE = "create table ";


    private void _createSQLTables() throws IOException {
        File sqlFolder = new File(sqlPath)
        if (!sqlFolder.exists()) {
            return;
        }

        File sqlFile = new File(sqlFolder.absolutePath + "/table.sql");


        for (int i = 0; i < entityList.size(); i++) {
            Entity entity = entityList.get(i);





            String createTableSQL = _getCreateTableSQL(entity);

        }

    }

    private String _getCreateTableSQL(Entity entity) {
        List<EntityColumn> pkList = entity.getPKList();
        List<EntityColumn> regularColList = entity.getRegularColList();

        if (regularColList.size() == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(_SQL_CREATE_TABLE);
        sb.append(entity.getTable());
        sb.append(" (\n");

        for (int i = 0; i < regularColList.size(); i++) {
            EntityColumn col = regularColList.get(i);

            String colName = col.getName();
            String colType = col.getType();
            String colIdType = col.getIdType();

            sb.append("\t");
            sb.append(col.getDBName());
            sb.append(" ");

            if (StringUtil.equalsIgnoreCase(colType, "boolean")) {
                sb.append("BOOLEAN");
            } else if (StringUtil.equalsIgnoreCase(colType, "double") ||
                    StringUtil.equalsIgnoreCase(colType, "float")) {

                sb.append("DOUBLE");
            } else if (colType.equals("int") ||
                    colType.equals("Integer") ||
                    StringUtil.equalsIgnoreCase(colType, "short")) {

                sb.append("INTEGER");
            } else if (StringUtil.equalsIgnoreCase(colType, "long")) {
                sb.append("LONG");
            } else if (colType.equals("Blob")) {
                sb.append("BLOB");
            } else if (colType.equals("Date")) {
                sb.append("DATE");
            } else if (colType.equals("String")) {
                Map<String, String> hints = ModelHintsUtil.getHints(
                        _packagePath + ".model." + entity.getName(), colName);

                int maxLength = 75;

                if (hints != null) {
                    maxLength = GetterUtil.getInteger(
                            hints.get("max-length"), maxLength);
                }

                if (col.isLocalized() && (maxLength < 4000)) {
                    maxLength = 4000;
                }

                if (maxLength < 4000) {
                    sb.append("VARCHAR(");
                    sb.append(maxLength);
                    sb.append(")");
                } else if (maxLength == 4000) {
                    sb.append("STRING");
                } else if (maxLength > 4000) {
                    sb.append("TEXT");
                }
            } else {
                sb.append("invalid");
            }

            if (col.isPrimary()) {
                sb.append(" not null");

                if (!entity.hasCompoundPK()) {
                    sb.append(" primary key");
                }
            } else if (colType.equals("Date") || colType.equals("String")) {
                sb.append(" null");
            }

            if (Validator.isNotNull(colIdType) &&
                    colIdType.equals("identity")) {

                sb.append(" IDENTITY");
            }

            if (((i + 1) != regularColList.size()) ||
                    entity.hasCompoundPK()) {

                sb.append(",");
            }

            sb.append("\n");
        }

        if (entity.hasCompoundPK()) {
            sb.append("\tprimary key (");

            for (int j = 0; j < pkList.size(); j++) {
                EntityColumn pk = pkList.get(j);

                sb.append(pk.getDBName());

                if ((j + 1) != pkList.size()) {
                    sb.append(", ");
                }
            }

            sb.append(")\n");
        }

        sb.append(");");

        return sb.toString();
    }

}
