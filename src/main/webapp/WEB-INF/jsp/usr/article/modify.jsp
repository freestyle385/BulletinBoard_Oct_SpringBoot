<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 수정" />

<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="table-box-type-1" method="POST" action="../article/doModify?id=${foundArticle.id}">
      <table>
        <colgroup>
          <col width="200">
        </colgroup>
        <tbody>
          <tr>
            <th>제목</th>
            <td>
              <input name="title" value="${foundArticle.title}" type="text" />
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea name="body" autocomplete="off" />${foundArticle.body}</textarea>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="btns mt-2">
        <button class="btn-text-link" type="button" onclick="history.back()">뒤로가기</button>
        <input type="submit" value="수정완료" />
      </div>
    </form>
  </div>
</section>
<%@ include file="../common/foot.jspf"%>

