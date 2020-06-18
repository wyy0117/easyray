package com.easyray.codegen

import freemarker.template.Configuration
import freemarker.template.Template
import org.junit.Before
import org.junit.Test

import java.text.SimpleDateFormat

/**
 * @Date: 20-1-26
 * @Author: wyy
 */
class CodeGen {
    private Configuration configuration;

    @Before
    void before() {
        configuration = new Configuration(Configuration.VERSION_2_3_30)
        configuration.setClassForTemplateLoading(this.class, "/template")
        configuration.setDefaultEncoding("UTF-8")
    }

    @Test
    void gen() {
        String entityName = "DFileVersion"
        String apiBasePackage = "com.easyray.documentapi"
        String providerBasePackge = "com.easyray.documentprovider"
        String basePath = CodeGen.classLoader.getResource(".").path
        List<String> pathList = basePath.split("/") as List
        pathList = pathList.subList(0, pathList.size() - 2)
        basePath = pathList.join("/") + "/src/test/resources"

        Map variableMap = [
                ENTITY               : entityName,
                DATE                 : new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                AUTHOR               : System.getProperty("user.name"),
                ENTITY_PACKAGE       : "${apiBasePackage}.entity",
                ERROR_PACKAGE        : "${apiBasePackage}.error",
                PROVIDER_PACKAGE     : "${apiBasePackage}.provider",
                MAPPER_PACKAGE       : "${providerBasePackge}.mapper",
                PROVIDER_IMPL_PACKAGE: "${providerBasePackge}.provider.impl"
        ]
        genCode(entityName, variableMap, basePath + "/entity", "Entity.ftl")
        genCode(entityName, variableMap, basePath + "/provider", "EntityCheckPermission.ftl")
        genCode(entityName, variableMap, basePath + "/provider/impl", "EntityCheckPermissionAspect.ftl")
        genCode(entityName, variableMap, basePath + "/provider/impl", "EntityCheckPermissionImpl.ftl")
        genCode(entityName, variableMap, basePath + "/error", "EntityErrorCode.ftl")
        genCode(entityName, variableMap, basePath + "/provider", "EntityLocalProvider.ftl")
        genCode(entityName, variableMap, basePath + "/provider/impl", "EntityLocalProviderImpl.ftl")
        genCode(entityName, variableMap, basePath + "/mapper", "EntityMapper.ftl")
        genCode(entityName, variableMap, basePath + "/provider", "EntityProvider.ftl")
        genCode(entityName, variableMap, basePath + "/provider/impl", "EntityProviderImpl.ftl")
    }


    private void genCode(String entityName, Map variableMap, String resFolderPath, String templateFileName) {
        Template template = configuration.getTemplate(templateFileName)
        File resFolder = new File(resFolderPath)
        if (!resFolder.exists()) {
            resFolder.mkdirs()
        }
        String resFileName = templateFileName.replace("Entity", entityName).replace("ftl", "java")
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(resFolder, resFileName)))
        template.process(variableMap, writer)
    }

}
