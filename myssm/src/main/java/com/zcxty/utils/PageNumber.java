package com.zcxty.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class PageNumber {
	 public  int count;
	 public  int pageNum;
	 public  int pageSize;
	 public  Map<String,String> param;
	 public  int currentPage;
 public PageNumber(int count,int pageSize,int page,Map<String,String> param) {
	 this.count=count;
	 this.pageSize=pageSize;
	 this.param=param;
	 if(count%pageSize==0) {
		   this.pageNum=(count/pageSize);
	   }else {
		   this.pageNum=(count/pageSize+1);  
	   }
	 if(Tool.isNumeric(page+"")) {
		 if(page>pageNum) {
		    	this.currentPage=pageNum;
		    }else if(page<=pageNum && page>0){
		    	this.currentPage=page;	
		    	}else {
		    	this.currentPage=1;
		    }
	 }else {
		 this.currentPage=1; 
	 }
    
 }
 public String getPage() {
  StringBuffer sbparm=new StringBuffer(); 
    if(param!=null) {
  Set<Entry<String,String>>  entrySet=param.entrySet();
    	for(Entry<String,String> entry:entrySet) {
    		sbparm.append('&'+entry.getKey()+'='+entry.getValue());
    	}
    }
    StringBuffer sb=new StringBuffer();
  if(currentPage>1) { 
	   sb.append("<span class=\"page-prev\"><a href=\"?page="+(currentPage-1));
	   sb.append(sbparm);
	   sb.append("\"><<</a></span>");
  }else{
	   sb.append("<span class=\"page-prev\"><a href=\"?page=1");
	   sb.append(sbparm);
	   sb.append("\"><<</a></span>");
  }
  if(currentPage==1) {  
       sb.append("<span class=\"current-page\" style=\"width:50px;!important\">start</span>");
  }else {
	   sb.append("<span class=\"page-start\" style=\"width:50px;!important\">");
	   sb.append("<a href=\"?page=1");
	   sb.append(sbparm);
	   sb.append("\">start</a></span>"); 
	  
  }
   if(pageNum-currentPage+1>=5) {
	for(int i=currentPage;i<currentPage+5;i++) {
		if(currentPage==i) {
			sb.append("<span class=\"current-page\">"+i+"</span>");
		}else {
			sb.append("<span class=\"page-num\"><a href=\"?page="+i);
			sb.append(sbparm);
			sb.append("\">"+i+"</a></span>");
		}
	 }
	}else if(pageNum-4>0){
		for(int i=pageNum-4;i<=pageNum;i++) {
			if(currentPage==i) {
				sb.append("<span class=\"current-page\">"+i+"</span>");
			}else {
				sb.append("<span class=\"page-num\"><a href=\"?page="+i);
				sb.append("\">"+i+"</a></span>");
			}
			
		}
		
	}else {
		for(int i= 1;i<=pageNum;i++) {
		if(currentPage==i) {
			sb.append("<span class=\"current-page\">");
			sb.append(sbparm);
			sb.append(+i+"</span>");
		}else {
			sb.append("<span class=\"page-num\"><a href=\"?page="+i);
			sb.append(sbparm);
			sb.append("\">"+i+"</a></span>");
		}
		
		}
	}
       if(currentPage==pageNum) {
    	   sb.append("<span class=\"current-page\">");
    	   sb.append("end</span>");  
       }else {
    	   sb.append("<span class=\"page-end\"><a href=\"?page="+(currentPage==0?1:pageNum));
    	   sb.append("\">end</a></span>");
       }
      
    
   if(currentPage<pageNum) {
	    sb.append("<span class=\"page-next\"><a href=\"?page="+(currentPage+1));
		sb.append(sbparm);
		sb.append("\">>></a></span>"); 
	   
   }else {
	    sb.append("<span class=\"page-next\"><a href=\"?page="+pageNum);
		sb.append(sbparm);
		sb.append("\">>></a></span>"); 
   }
    	sb.append("<input type=\"text\" class=\"search-page\" id=\"pagenum\" name=\"page\">"
    			+ "<input type=\"submit\" class=\"search-button\" onclick=\"pageJump()\" value=\"GO\">");
        sb.append("<script>"
        		+ " function pageJump(){"
        		+"var num=document.getElementById(\"pagenum\").value;\r\n"
        		+"window.location.href=\"http://localhost:8080/myssm/user/allUser?page=\"+num\r\n"
        		+ "}</script>");
       
      sb.append("<span class=\"page-total\">共"+pageNum+"页</span>");
       return sb.toString();
	 
 }
}
