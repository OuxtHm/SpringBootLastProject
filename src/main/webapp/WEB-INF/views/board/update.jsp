<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="breadcumb-area" style="background-image: url(/img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>자유 게시판</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80" style="height: 450px">
        <div class="container">
            <div class="row" style="width: 800px; margin: 0px auto;">
            	<form method="post" action="/board/update_ok">
	            	<table class="table">
	            		<tbody>
	            			<tr>
		            			<th width="15%" class="danger">이름</th>
		            			<td width="85%" class="text-left">
		            				<input type="text" name=name size=20 class="input-sm" value="${vo.name }">
		            				<input type="hidden" name=no size=20 value="${vo.no }">		            				
		            			</td>
		            		</tr>
	            			<tr>
		            			<th width="15%" class="danger">제목</th>
		            			<td width="85%" class="text-left">
		            				<input type="text" name=subject size=60 class="input-sm" value="${vo.subject }">
		            			</td>
		            		</tr>
	            			<tr>
		            			<th width="15%" class="danger">내용</th>
		            			<td width="85%" class="text-left">
		            				<textarea cols="60" rows="10" name=content>${vo.content }</textarea>
		            			</td>
		            		</tr>
	            			<tr>
		            			<th width="15%" class="danger">비밀번호</th>
		            			<td width="85%" class="text-left">
		            				<input type="password" name=pwd size=20 class="input-sm">
		            			</td>
		            		</tr>
		            		<tr>
		            			<td class="text-center" colspan="2">
		            				<button class="btn-sm btn-primary" type="submit">글쓰기</button>
		            				<button class="btn-sm btn-primary" onclick="javascript:history.back()" type="button">취소</button>
		            			</td>
		            		</tr>
	            		</tbody>
	            	</table>
            	</form>
            </div>
        </div>
    </section>
</body>
</html>