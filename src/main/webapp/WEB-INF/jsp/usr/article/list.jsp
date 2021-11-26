<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name} 게시물 리스트" />

<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <div>게시물 개수 : ${articlesCount } 개</div>
    <div class="table-box-type-1">
      <table border="1">
        <colgroup>
          <col width="80">
          <col width="150">
          <col width="150">
          <col width="150">
          <col>
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>작성날짜</th>
            <th>수정날짜</th>
            <th>작성자</th>
            <th>제목</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="article" items="${articles }">
            <tr>
              <td>${article.id }</td>
              <td>${article.regDate.substring(2, 16) }</td>
              <td>${article.updateDate.substring(2, 16) }</td>
              <td>${article.extra__writerName }</td>
              <td>
                <a class="btn-text-link" href="../article/detail?id=${article.id }">${article.title }</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="btns mt-2">
      <a class="btn-text-link" href="../article/write">글쓰기</a>
    </div>
    <div class="page-menu my-3">
      <div class="btn-group justify-center">
        <c:set var="pageMenuArmLen" value="4" />
        <c:set var="startPage" value="${page - pageMenuArmLen >= 1 ? page - pageMenuArmLen : 1}" />
        <c:set var="endPage" value="${page + pageMenuArmLen <= pagesCount ? page + pageMenuArmLen : pagesCount}" />
        <c:if test="${startPage > 1}">
          <a class="btn btn-sm" href="?boardId=${board.id}&page=1">1</a>
        </c:if>
        <c:if test="${startPage > 2}">
          <a class="btn btn-sm btn-disabled">...</a>
        </c:if>
        
        <c:forEach begin="${startPage }" end="${endPage }" var="i">
          <a class="btn btn-sm ${param.page == i ? 'btn-active' : ''}" href="?boardId=${board.id}&page=${i}">${i}</a>
        </c:forEach>
        
        <c:if test="${endPage < pagesCount}">
          <c:if test="${endPage < pagesCount - 1}">
            <a class="btn btn-sm btn-disabled">...</a>
          </c:if>
          <a class="btn btn-sm" href="?boardId=${board.id}&page=${pagesCount }">${pagesCount }</a>
        </c:if>
      </div>
    </div>
  </div>
</section>


<%@ include file="../common/foot.jspf"%>

<style type="text/css">
.page>a.red {
	color: red;
}
</style>
