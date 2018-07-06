package com.fpfos;

import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fu Pengfei on 2018/7/6.
 */
public class A {

    public static void bb() throws Exception {

        List<String> urls = new ArrayList<String>();
//        urls.add("https://www.kaola.com/brand/1464.html?changeContent=isSelfProduct&pageSize=60&pageNo=1&sortfield=2&isStock=false&isSelfProduct=true&isPromote=false&isTaxFree=false&isDesc=true&proIds=&lowerPrice=-1&upperPrice=-1&isBrand=0&headCategoryId=&backCategory=&key=&#search_crumbs");
//        urls.add("https://www.kaola.com/brand/1464.html?pageSize=60&pageNo=2&sortfield=2&isStock=false&isSelfProduct=true&isPromote=false&isTaxFree=false&isDesc=true&proIds=&lowerPrice=-1&upperPrice=-1&isBrand=0&headCategoryId=&backCategory=&key=&#topTab");
//        urls.add("https://www.kaola.com/brand/1464.html?pageSize=60&pageNo=3&sortfield=2&isStock=false&isSelfProduct=true&isPromote=false&isTaxFree=false&isDesc=true&proIds=&lowerPrice=-1&upperPrice=-1&isBrand=0&headCategoryId=&backCategory=&key=&#topTab");
//        urls.add("https://www.kaola.com/brand/1464.html?pageSize=60&pageNo=4&sortfield=2&isStock=false&isSelfProduct=true&isPromote=false&isTaxFree=false&isDesc=true&proIds=&lowerPrice=-1&upperPrice=-1&isBrand=0&headCategoryId=&backCategory=&key=&#topTab");
//        urls.add("https://www.kaola.com/brand/1464.html?pageSize=60&pageNo=5&sortfield=2&isStock=false&isSelfProduct=true&isPromote=false&isTaxFree=false&isDesc=true&proIds=&lowerPrice=-1&upperPrice=-1&isBrand=0&headCategoryId=&backCategory=&key=&#topTab");
//        urls.add("https://www.kaola.com/brand/1464.html?pageSize=60&pageNo=6&sortfield=2&isStock=false&isSelfProduct=true&isPromote=false&isTaxFree=false&isDesc=true&proIds=&lowerPrice=-1&upperPrice=-1&isBrand=0&headCategoryId=&backCategory=&key=&#topTab");
//        urls.add("https://www.kaola.com/brand/1464.html?pageSize=60&pageNo=7&sortfield=2&isStock=false&isSelfProduct=true&isPromote=false&isTaxFree=false&isDesc=true&proIds=&lowerPrice=-1&upperPrice=-1&isBrand=0&headCategoryId=&backCategory=&key=&#topTab");
//        urls.add("https://www.kaola.com/brand/1464.html?pageSize=60&pageNo=8&sortfield=2&isStock=false&isSelfProduct=true&isPromote=false&isTaxFree=false&isDesc=true&proIds=&lowerPrice=-1&upperPrice=-1&isBrand=0&headCategoryId=&backCategory=&key=&#topTab");
//        urls.add("https://www.kaola.com/brand/1464.html?pageSize=60&pageNo=9&sortfield=2&isStock=false&isSelfProduct=true&isPromote=false&isTaxFree=false&isDesc=true&proIds=&lowerPrice=-1&upperPrice=-1&isBrand=0&headCategoryId=&backCategory=&key=&#topTab");

        int i = 0;
        List<BBB> list = new ArrayList<BBB>();
        for (String url : urls) {
            i++;
            System.out.println("第" + i + "次取数据");
            list.addAll(cc(url));
            Thread.sleep(2000);
        }

        System.out.println("总共：" + list.size());

        System.out.println(new Gson().toJson(list));

        exportExcel(list);

    }

    public static List<BBB> cc(String url) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("ul#result");
        Elements ObjEle1 = elements.first().select("li.goods");
        System.out.println("本次总共抓出 : " + ObjEle1.size());

        List<BBB> list = new ArrayList<BBB>();

        for (int i = 0; i < ObjEle1.size(); i++) {
            Element item = ObjEle1.get(i);

            Element a = item.select("a").first();
            Element img = item.select("img").first();


            String jia = item.select("span.cur").first().text();
            String comments = item.select("a.comments").first().text();
            String title = a.attr("title");
            String imgS = img.attr("data-src");

            BBB bbb = new BBB();
            bbb.setName(title);
            bbb.setImage("https:" + imgS);
            bbb.setComments(comments);
            bbb.setPrice(jia);
            list.add(bbb);
//            System.out.println("名称："+title);
//            System.out.println("价格："+jia);
//            System.out.println("评价数量："+comments);
//            System.out.println("图片："+imgS);
//            System.out.println("====================================================================================");
        }

        return list;
    }


    public static void exportExcel(List<BBB> list) throws Exception {

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("数据");
        sheet.setColumnWidth(1,15 * 256);
        sheet.setDefaultRowHeight((short) 1000);
//        sheet.setcolumn
//// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
// 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("评论数");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("图片");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("价格");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("名称");
        cell.setCellStyle(style);


        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

// 第五步，写入实体数据 实际应用中这些数据从数据库得到，

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
//            row.setHeight((short)20);
            BBB item =  list.get(i);
// 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue( item.getComments());


            HSSFCell dataCell0 = row.createCell(1);//每行的第一列存放照片
            drawPictureInfoExcel(wb,patriarch,i+1,item.getImage());

            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short) 2, 2, (short) 1, 2);
            patriarch.createPicture(anchor, wb.addPicture(aaaaa(item.getImage()), HSSFWorkbook.PICTURE_TYPE_JPEG));

//            row.createCell((short) 1).setCellValue(item.getImage());
            row.createCell((short) 2).setCellValue(item.getPrice());
            row.createCell((short) 3).setCellValue(item.getName());



//            cell = row.createCell((short) 3);
//            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu
//                    .getBirth()));
        }
// 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("D:/yuki999.xls");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  static void drawPictureInfoExcel(HSSFWorkbook wb,HSSFPatriarch patriarch,int rowIndex,String pictureUrl)
    {
//rowIndex代表当前行
        try {

            if(!StringUtils.isEmpty(pictureUrl)) {
                URL url = new URL(pictureUrl);
                //打开链接
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                //设置请求方式为"GET"
                conn.setRequestMethod("GET");
                //超时响应时间为5秒
                conn.setConnectTimeout(5 * 1000);
                //通过输入流获取图片数据
                InputStream inStream = conn.getInputStream();
                //得到图片的二进制数据，以二进制封装得到数据，具有通用性
                byte[] data = readInputStream(inStream);
                //anchor主要用于设置图片的属性
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 250,(short) 1, rowIndex, (short) 1, rowIndex);
                //Sets the anchor type （图片在单元格的位置）
                //0 = Move and size with Cells, 2 = Move but don't size with cells, 3 = Don't move or size with cells.
                anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
                patriarch.createPicture(anchor, wb.addPicture(data, HSSFWorkbook.PICTURE_TYPE_JPEG));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] aaaaa(String imageusrl) throws Exception {

        URL url = new URL(imageusrl);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);

        return data;
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    public static class BBB implements Serializable {

        private String price;
        private String image;
        private String comments;
        private String name;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {

        if (true) {
            bb();
            return;
        }

        try {
            //创建一个URL实例
            URL url = new URL("https://www.kaola.com/brand/1464.html?pageSize=60&pageNo=1&sortfield=2&isStock=false&isSelfProduct=false&isPromote=false&isTaxFree=false&isDesc=true&proIds=&lowerPrice=-1&upperPrice=-1&isBrand=0&headCategoryId=&backCategory=&key=&#topTab");

            try {
                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");

                //为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据
                StringBuilder sb = new StringBuilder();
                while (data != null) {//循环读取数据

                    sb.append(data + "\r\n");
                    data = br.readLine();

                }


//                String aa= sb.toString().split("<div class=\"m-result\" id=\"searchresult\">")[1].split("<div class=\"splitPages\">")[0];

                System.out.println(sb.toString());
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
