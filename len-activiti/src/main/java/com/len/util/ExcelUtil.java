package com.len.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    /**
     * 导入Excel
     *
     * @param inputstream
     *            Excel数据流
     * @param clazz
     *            注解的实体类
     * @return 导入的数据
     */
    public static <T> List<T> importExcel(InputStream inputstream, Class<T> clazz) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setNeedSave(true);
        try {
            return ExcelImportUtil.importExcel(inputstream, clazz, params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 导出Excel
     *
     * @param excelTitle
     *            Excel标题
     * @param realPath
     *            真实路径
     * @param path
     *            响应路径
     * @param clazz
     *            注解的实体类
     * @param excels
     *            注解的实体类集合
     * @return 响应结果
     */
    public static <T> JsonUtil exportExcel(String excelTitle, String realPath, String path, Class<T> clazz,
                                             List<T> excels) {
        JsonUtil resultJson = new JsonUtil();
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(excelTitle, excelTitle), clazz, excels);
            String filename = excelTitle + "-" + new Date().toString() + ".xls";
            File dirPath = new File(realPath);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
            try {
                FileOutputStream outputStream = new FileOutputStream(realPath + "/" + filename);
                workbook.write(outputStream);
                resultJson.setStatus(0);
                resultJson.setData(path + "/" + filename);
            } catch (Exception e) {
                e.printStackTrace();
                resultJson.setStatus(1);
                return resultJson;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.setStatus(1);
            return resultJson;
        }
        return resultJson;
    }

}
