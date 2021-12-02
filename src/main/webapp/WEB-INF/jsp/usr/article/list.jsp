<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name} 게시물 리스트" />

<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <div class="flex">
      <div>게시물 개수 : ${articlesCount } 개</div>
      <div class="flex-grow"></div>
      <form>
        <input type="hidden" name="boardId" value="${param.boardId}" />
        <select data-value="${param.searchKeywordTypeCode}" name="searchKeywordTypeCode"
          class="select select-ghost select-bordered">
          <option disabled="disabled">검색유형</option>
          <option value="title">제목</option>
          <option value="body">내용</option>
          <option value="title,body">제목+내용</option>
        </select>

        <input name="searchKeyword" type="text" class="ml-2 w-60 input input-bordered" placeholder="검색어를 입력해주세요"
          maxlength="20" value="${param.searchKeyword}" />
        <button class="ml-3 btn btn-outline btn-ghost" type="submit">검색</button>
      </form>

    </div>
    <div class="table-box-type-1">
      <table border="1">
        <colgroup>
          <col width="50">
          <col width="100">
          <col width="100">
          <col width="75">
          <col width="150">
          <col width="50">
          <col>
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>작성날짜</th>
            <th>수정날짜</th>
            <th>작성자</th>
            <th>제목</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="article" items="${articles }">
            <tr>
              <td>${article.id }</td>
              <td>${article.forPrintType2RegDate }</td>
              <td>${article.forPrintType2UpdateDate }</td>
              <td>${article.extra__writerName }</td>
              <td>
                <a class="btn-text-link" href="../article/detail?id=${article.id }">${article.title }</a>
              </td>
              <td>${article.hitCount}</td>
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

        <c:set var="pageBaseUri" value="?boardId=${board.id }" />
        <c:set var="pageBaseUri" value="${pageBaseUri }&searchKeywordTypeCode=${param.searchKeywordTypeCode }" />
        <c:set var="pageBaseUri" value="${pageBaseUri }&searchKeyword=${param.searchKeyword }" />

        <c:if test="${startPage > 1}">
          <a class="btn btn-sm" href="${pageBaseUri}&page=1">1</a>
        </c:if>
        <c:if test="${startPage > 2}">
          <a class="btn btn-sm btn-disabled">...</a>
        </c:if>

        <c:forEach begin="${startPage }" end="${endPage }" var="i">
          <a class="btn btn-sm ${param.page == i ? 'btn-active' : ''}" href="${pageBaseUri}&page=${i}">${i}</a>
        </c:forEach>

        <c:if test="${endPage < pagesCount}">
          <c:if test="${endPage < pagesCount - 1}">
            <a class="btn btn-sm btn-disabled">...</a>
          </c:if>
          <a class="btn btn-sm" href="${pageBaseUri}&page=${pagesCount }">${pagesCount }</a>
        </c:if>
      </div>
    </div>
  </div>
</section>


<%@ include file="../common/foot.jspf"%>
