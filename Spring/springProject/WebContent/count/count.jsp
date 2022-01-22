<%@page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	int count = 0;
	String path = "";
	public void jspInit(){
		// this : 현재 페이지
		path = this.getServletContext().getRealPath("count/count.txt");
		FileReader fr = null;
		BufferedReader br = null;
		try{
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line = br.readLine();
			if(line != null){
				count = Integer.parseInt(line.trim());
			}
			br.close();
			//fr.close();
		} catch(FileNotFoundException e){
			System.err.println(e.getMessage());
			countSaveFile();
		} catch(IOException e){
			System.err.println(e.getMessage());
			countSaveFile();
		}
	}
	
	public void countSaveFile(){
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
			fw = new FileWriter(path);
			pw = new PrintWriter(fw, true);
			pw.println(count);
			pw.close();
			//fw.close();
		} catch(IOException e){
			System.err.println(e.getMessage());
		}
	}
	
	public void jspDestroy(){
		countSaveFile();
	}
%>
<%
	if(session.isNew()){
		count++;
	}
	if(count % 10 == 0){
		countSaveFile();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<b>방문 횟수 : </b>
<%	String countStr = String.valueOf(count);
	for(int i = 0; i < countStr.length(); i++){	%>
	<img src="<%= request.getContextPath() %>/img/no<%= countStr.charAt(i) %>.gif">
<%	}	%>
</body>
</html>