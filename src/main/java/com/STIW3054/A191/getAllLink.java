/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author WANG LUO
 */
public class GetLink {
    /** 
 * 根据jsoup方法获取htmlContent 
 * @throws IOException  
 */  
public String getContentByJsoup(String url){  
    String content="";  
    try {  
        Document doc=Jsoup.connect(url)  
        .data("jquery", "java")  
        .userAgent("Mozilla")  
        .cookie("auth", "token")  
        .timeout(50000)  
        .get();   
        content=doc.toString();//获取iteye网站的源码html内容   
    } catch (IOException e) {  
        e.printStackTrace();  
    }    
    return content;  
}
      /** 
 * 使用jsoup来对文档分析 
        * 获取目标内容所在的目标层 
        * 这个目标层可以是div，table，tr等等 
     * @param content
     * @return 
 */  
public String getDivContentByJsoup(String content){  
    String divContent="";  
    Document doc=Jsoup.parse(content);  
    Elements divs=doc.getElementsByClass("js-timeline-item js-timeline-progressive-focus-container");  
    divContent=divs.toString();    
    return divContent;  
}
/** 
     * 使用jsoup分析divContent 
     * 1.获取链接 2.获取url地址（绝对路径） 
     */  
    public List<String> getLinksByJsoup(){  
        String abs="https://github.com/STIW3054-A191/Assignments/issues/1";  
        String HtmlContent=getContentByJsoup(abs);  
        String divContent=getDivContentByJsoup(HtmlContent);
        Document doc=Jsoup.parse(divContent,abs);  
        Elements linkStrs=doc.getElementsByClass("d-block comment-body markdown-body  js-comment-body");
        List<String> urlList=new ArrayList<String>();
        for(Element linkStr:linkStrs){  
            String url=linkStr.getElementsByTag("a").attr("abs:href");   
//            System.out.println("url:"+url);
            urlList.add(url);
        }  
        return urlList;
    }
}
