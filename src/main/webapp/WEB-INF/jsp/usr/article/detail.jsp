<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 상세조회" />

<%@ include file="../common/head.jspf"%>

<script>
	const params = {};
	params.id = parseInt('${param.id}');
</script>

<script>
	function ArticleDetail__increaseHitCount() {
		const localStorageKey = 'article__' + params.id + '__viewDone';

		if (localStorage.getItem(localStorageKey)) {
			return;
		}
		localStorage.setItem(localStorageKey, true);

		$.get('../article/doIncreaseHitCount', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(hitCount) {
			$('.article-detail__hit-count').empty().html(hitCount);
		}, 'json');
	}
	$(function() {
		ArticleDetail__increaseHitCount();
	})
</script>

<script>
	$(document).ready( function() {
    	 $(function(){
    	    	$('.add-goodRp-btn').click(function(){
    	    		$.ajax({
    	    			url: "/usr/article/increaseGoodRp",
    	                type: "POST",
    	                data: {
    	                    id: params.id
    	                },
    	                success: function (goodReactionPoint) {
    	                	$('.add-goodRp').html(goodReactionPoint);
    	                },
    	    		})
    	    	})
    	 });
	});
	$(document).ready( function() {
   	 $(function(){
   	    	$('.add-badRp-btn').click(function(){
   	    		$.ajax({
   	    			url: "/usr/article/increaseBadRp",
   	                type: "POST",
   	                data: {
   	                    id: params.id
   	                },
   	                success: function (badReactionPoint) {
   	                	$('.add-badRp').html(badReactionPoint);
   	                },
   	    		})
   	    	})
   	 });
	});
</script>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <div class="table-box-type-1">
      <table>
        <colgroup>
          <col width="200">
        </colgroup>
        <tbody>
          <tr>
            <th>번호</th>
            <td>${foundArticle.id}</td>
          </tr>
          <tr>
            <th>작성날짜</th>
            <td>${foundArticle.forPrintType2RegDate}</td>
          </tr>
          <tr>
            <th>수정날짜</th>
            <td>${foundArticle.forPrintType2UpdateDate}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${foundArticle.extra__writerName}</td>
          </tr>
          <tr>
            <th>조회수</th>
            <td>
              <span class="article-detail__hit-count">${foundArticle.hitCount}</span>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>${foundArticle.title}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td>${foundArticle.body}</td>
          </tr>
          <tr>
            <th>추천</th>
            <td>
              <span class="add-goodRp-btn btn btn-outline">좋아요👍<span class="add-goodRp ml-2">${foundArticle.goodReactionPoint}</span></span>
              
              <span class="add-badRp-btn ml-5 btn btn-outline">싫어요👎<span class="add-badRp ml-2">${foundArticle.badReactionPoint}</span></span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="btns mt-2">
      <button class="btn-text-link" type="button" onclick="location.replace('list');">뒤로가기</button>
      <a class="btn-text-link" href="../article/modify?id=${foundArticle.id }">수정하기</a>
      <a class="btn-text-link" onclick="if (confirm('게시물을 삭제하시겠습니까?') == false) {return false};"
        href="../article/doDelete?id=${foundArticle.id }">삭제하기</a>
    </div>
  </div>
</section>
<%@ include file="../common/foot.jspf"%>

