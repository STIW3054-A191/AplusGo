/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.spinellis.ckjm;

//import gr.spinellis.ckjm.Data;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Workbook;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;


/**
 *
 * @author Tan Yee Chain
 */
public class Test2 {

    private static final List<Data> softwareMetrics = new ArrayList();
    private static final List<Data3> softwareMetricsTotal = new ArrayList();
    private static final List<Data2> matricname = new ArrayList();

    public void MatricName(String matricNum, String name) {
        matricname.add(new Data2(matricNum, name));
    }

    public void test2(int w, int d, int n, int b, int r, int l) {
        softwareMetrics.add(new Data(w, d, n, b, r, l));

    }

    void printData() {

        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", softwareMetrics.get(softwareMetrics.size() - 1).getData0(), softwareMetrics.get(softwareMetrics.size() - 1).getData1(), softwareMetrics.get(softwareMetrics.size() - 1).getData2(), softwareMetrics.get(softwareMetrics.size() - 1).getData3(), softwareMetrics.get(softwareMetrics.size() - 1).getData4(), softwareMetrics.get(softwareMetrics.size() - 1).getData5());
        ckjmTotal(softwareMetrics.get(softwareMetrics.size() - 1).getData0(), softwareMetrics.get(softwareMetrics.size() - 1).getData1(), softwareMetrics.get(softwareMetrics.size() - 1).getData2(), softwareMetrics.get(softwareMetrics.size() - 1).getData3(), softwareMetrics.get(softwareMetrics.size() - 1).getData4(), softwareMetrics.get(softwareMetrics.size() - 1).getData5());
//softwareMetricsTotal.add(new Data(softwareMetrics.get(softwareMetrics.size() - 1).getData0(), softwareMetrics.get(softwareMetrics.size() - 1).getData1(), softwareMetrics.get(softwareMetrics.size() - 1).getData2(), softwareMetrics.get(softwareMetrics.size() - 1).getData3(), softwareMetrics.get(softwareMetrics.size() - 1).getData4(), softwareMetrics.get(softwareMetrics.size() - 1).getData5()));
    }
    void ckjmTotal(int w, int d, int n, int b, int r, int l){
        softwareMetricsTotal.add(new Data3(w, d, n, b, r, l));
    }

    public void writeToExcel() {
        if (softwareMetrics.isEmpty()) {
            System.out.println("ERROR : No data to write, build terminated.");
            System.exit(0);
        }
        String excelFile = "STIW3054_A191_Project.xls";
        System.out.println("=================================");
        System.out.println("Writing the " + excelFile + "...");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Student_Project_Ckjm");
        try {
            int k = 0;
            do {
                if (k == 1) {
                    for (int i = 0; i < matricname.size(); i++) {
                        HSSFRow row = sheet.createRow(i+1);
                        HSSFCell cell1 = row.createCell(0);
                        cell1.setCellValue(i + 1);
                        HSSFCell cell2 = row.createCell(1);
                        cell2.setCellValue(matricname.get(i).getDataM());
                        HSSFCell cell3 = row.createCell(2);
                        cell3.setCellValue(matricname.get(i).getDataN());
                        HSSFCell cell4 = row.createCell(3);
                        cell4.setCellValue(softwareMetricsTotal.get(i).getData0());
                        HSSFCell cell5 = row.createCell(4);
                        cell5.setCellValue(softwareMetricsTotal.get(i).getData1());
                        HSSFCell cell6 = row.createCell(5);
                        cell6.setCellValue(softwareMetricsTotal.get(i).getData2());
                        HSSFCell cell7 = row.createCell(6);
                        cell7.setCellValue(softwareMetricsTotal.get(i).getData3());
                        HSSFCell cell8 = row.createCell(7);
                        cell8.setCellValue(softwareMetricsTotal.get(i).getData4());
                        HSSFCell cell9 = row.createCell(8);
                        cell9.setCellValue(softwareMetricsTotal.get(i).getData5());
                    }
                    k++;
                } else {
                    HSSFRow row = sheet.createRow(0);
                    HSSFCell cell1 = row.createCell(0);
                    cell1.setCellValue("No");
                    HSSFCell cell2 = row.createCell(1);
                    cell2.setCellValue("Matric No");
                    HSSFCell cell3 = row.createCell(2);
                    cell3.setCellValue("Name");
                    HSSFCell cell4 = row.createCell(3);
                    cell4.setCellValue("WMC");
                    HSSFCell cell5 = row.createCell(4);
                    cell5.setCellValue("DIT");
                    HSSFCell cell6 = row.createCell(5);
                    cell6.setCellValue("NOC");
                    HSSFCell cell7 = row.createCell(6);
                    cell7.setCellValue("CBD");
                    HSSFCell cell8 = row.createCell(7);
                    cell8.setCellValue("RFC");
                    HSSFCell cell9 = row.createCell(8);
                    cell9.setCellValue("LCOM");
                    k++;
                }
            } while (k <= 1);
            FileOutputStream outputFile = new FileOutputStream("D:/realDemo/realProject.xls");
            workbook.write(outputFile);
            outputFile.flush();
            outputFile.close();
            System.out.println("=================================");
            System.out.println(excelFile + " Is written successfully.");
        } catch (IOException e) {
            System.out.println("ERROR : Failed to write the file!");
        }
    }
    
    public void makeBarChart() throws FileNotFoundException, IOException, BiffException{
        jxl.Workbook readwb = null;  
        // 构建Workbook对象, 只读Workbook对象 直接从本地文件创建Workbook  
            readwb = Workbook.getWorkbook(new FileInputStream(new File("D:\\realDemo\\realProject.xls")));  
            // Sheet的下标是从0开始 获取第一张Sheet表  
            Sheet readsheet = readwb.getSheet(0);  
            // 获取Sheet表中所包含的总列数  
            int rsColumns = readsheet.getColumns();  
            // 获取Sheet表中所包含的总行数  
            int rsRows = readsheet.getRows();  
            // 获取指定单元格的对象引用  
            String[][] arr = (new String[rsColumns][rsRows]);
            double[][] data = (new double[rsColumns-3][rsRows-1]);
            String[] rowKeys = { "WMC", "DIT","NOC","CBD","RFC","LCOM" };
            String[] columnKeys = { "1", "2", "3", "4", "5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27"};
            for (int i = 0; i < rsColumns; i++) {  
                for (int j = 0; j < rsRows; j++) {  
                    Cell cell = readsheet.getCell(i, j);  
                    arr[i][j] = cell.getContents();
//                    System.out.println(arr[i][j]);
                }  
            }  
            for (int i = 0; i < rsColumns-3; i++) {  
                for (int j = 0; j < rsRows-1; j++) {  
                    
                    data[i][j] = Double.valueOf(arr[i+3][j+1]);
//                    System.out.print(data[i][j] + " ");
                }   
            }
        CategoryDataset dataset = getBarData(data, rowKeys, columnKeys);
        createStackedBarChart(dataset, "x", "y", "Bar Chart", "stsckedBar.png");
    }
    public CategoryDataset getBarData(double[][] data, String[] rowKeys,
            String[] columnKeys) {
        return DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
    }
    public String createStackedBarChart(CategoryDataset dataset, String xName,
            String yName, String chartTitle, String charName) {
        // 1:得到 CategoryDataset

        // 2:JFreeChart对象
        JFreeChart chart = ChartFactory.createStackedBarChart(chartTitle, // 图表标题
                xName, // 目录轴的显示标签
                yName, // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true, // 是否显示图例(对于简单的柱状图必须是false)
                false, // 是否生成工具
                false // 是否生成URL链接
                );
        // 图例字体清晰
        chart.setTextAntiAlias(false);

        chart.setBackgroundPaint(Color.WHITE);

        // 2 ．2 主标题对象 主标题对象是 TextTitle 类型
        chart
                .setTitle(new TextTitle(chartTitle, new Font("隶书", Font.BOLD,
                        25)));
        // 2 ．2.1:设置中文
        // x,y轴坐标字体
        Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);

        // 2 ．3 Plot 对象 Plot 对象是图形的绘制结构对象
        CategoryPlot plot = chart.getCategoryPlot();

        // 设置横虚线可见
        plot.setRangeGridlinesVisible(true);
        // 虚线色彩
        plot.setRangeGridlinePaint(Color.gray);

        // 数据轴精度
        NumberAxis vn = (NumberAxis) plot.getRangeAxis();
        // 设置最大值是1
        vn.setUpperBound(1);
        // 设置数据轴坐标从0开始
        // vn.setAutoRangeIncludesZero(true);
        // 数据显示格式是百分比
        DecimalFormat df = new DecimalFormat("#0.00");
        vn.setNumberFormatOverride(df); // 数据轴数据标签的显示格式
        // DomainAxis （区域轴，相当于 x 轴）， RangeAxis （范围轴，相当于 y 轴）
        CategoryAxis domainAxis = plot.getDomainAxis();

        domainAxis.setLabelFont(labelFont);// 轴标题
        domainAxis.setTickLabelFont(labelFont);// 轴数值

        // x轴坐标太长，建议设置倾斜，如下两种方式选其一，两种效果相同
        // 倾斜（1）横轴上的 Lable 45度倾斜
        // domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        // 倾斜（2）Lable（Math.PI 3.0）度倾斜
        // domainAxis.setCategoryLabelPositions(CategoryLabelPositions
        // .createUpRotationLabelPositions(Math.PI / 3.0));

        domainAxis.setMaximumCategoryLabelWidthRatio(0.6f);// 横轴上的 Lable 是否完整显示

        plot.setDomainAxis(domainAxis);

        // y轴设置
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(labelFont);
        rangeAxis.setTickLabelFont(labelFont);
        // 设置最高的一个 Item 与图片顶端的距离
        rangeAxis.setUpperMargin(0.1);
        // 设置最低的一个 Item 与图片底端的距离
        rangeAxis.setLowerMargin(0.1);
        rangeAxis.setRange(0,400);
        plot.setRangeAxis(rangeAxis);

        // Renderer 对象是图形的绘制单元
        StackedBarRenderer renderer = new StackedBarRenderer();
        // 设置柱子宽度
        renderer.setMaximumBarWidth(0.05);
        // 设置柱子高度
        renderer.setMinimumBarLength(0.1);
        // 设置柱的边框颜色
        renderer.setBaseOutlinePaint(Color.BLACK);
        // 设置柱的边框可见
        renderer.setDrawBarOutline(true);

        // // 设置柱的颜色(可设定也可默认)
        renderer.setSeriesPaint(0, new Color(204, 255, 204));
        renderer.setSeriesPaint(1, new Color(255, 204, 153));

        // 设置每个地区所包含的平行柱的之间距离
        renderer.setItemMargin(0.4);

        plot.setRenderer(renderer);
        // 设置柱的透明度(如果是3D的必须设置才能达到立体效果，如果是2D的设置则使颜色变淡)
        // plot.setForegroundAlpha(0.65f);

        FileOutputStream fos_jpg = null;
        try {
            String CHART_PATH = "D:\\realDemo\\";
            String chartName = CHART_PATH + charName;
            fos_jpg = new FileOutputStream(chartName);
            ChartUtilities.writeChartAsPNG(fos_jpg, chart, 1000, 1000, true, 10);
            return chartName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                fos_jpg.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
