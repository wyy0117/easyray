package com.easyray.codegen

import org.junit.Test

import java.text.SimpleDateFormat

/**
 * @Date: 20-1-26
 * @Author: wyy
 */
class CodeGen {
    @Test
    void gen() {
        String entity = "Team"
        String basePath = CodeGen.classLoader.getResource(".").path
        List<String> pathList = basePath.split("/") as List
        pathList = pathList.subList(0, pathList.size() - 2)
        basePath = pathList.join("/") + "/src/test/resources"

        genCode(entity, basePath, "entity")
        genCode(entity, basePath, "mapper")
        genCode(entity, basePath, "provider")
        genCode(entity, basePath, "provider/impl")
        genCode(entity, basePath, "error")
    }

    private void genCode(String entity, String basePath, String folderName) {
        File srcFolder = new File(basePath + "/template/${folderName}")
        File resFolder = new File(basePath + "/${folderName}")
        if (!resFolder.exists()) {
            resFolder.mkdirs()
        } else {
            resFolder.listFiles(new FileFilter() {
                @Override
                boolean accept(File pathname) {
                    return pathname.isFile()
                }
            }).each {
                it.delete()
            }
        }

        srcFolder.listFiles(new FileFilter() {
            @Override
            boolean accept(File pathname) {
                return pathname.isFile()
            }
        }).each {
            File file = new File(resFolder, it.name.replace("Entity", entity))
            file.text = replace(it.text, entity)
        }
    }


    private String replace(String src, String entity) {
        String date = new SimpleDateFormat("yyyy-MM_dd").format(new Date())
        String author = System.getProperty("user.name")
        src.replace('${DATE}', date).replace('${AUTHOR}', author).replace('${ENTITY}', entity)
    }


}
