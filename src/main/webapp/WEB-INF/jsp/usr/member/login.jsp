<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="계정 로그인" />

<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="table-box-type-1" method="POST" action="../member/doLogin">
      <table>
        <colgroup>
          <col width="200">
        </colgroup>
        <tbody>
          <tr>
            <th>아이디</th>
            <td>
              <input name="loginId" class="w-60" type="text" placeholder="아이디를 입력해주세요" />
            </td>
          </tr>
          <tr>
            <th>비밀번호</th>
            <td>
              <input name="loginPw" class="w-60" type="password" placeholder="비밀번호를 입력해주세요" />
            </td>
          </tr>
        </tbody>
      </table>
      <div class="btns mt-2">
        <button class="btn-text-link" type="button" onclick="history.back();">뒤로가기</button>
        <input type="submit" value="로그인하기" />
      </div>
    </form>
  </div>
</section>
<%@ include file="../common/foot.jspf"%>

