<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<c:if test="${!empty msg}">
    <script>
        alert("${msg}");
    </script>
</c:if>
<script type="text/javascript">
    $(document).ready(function () {

        if("${key}")
            $("#skey").val("${key}").prop("selected", true);

        $("#mvRegisterBtn").click(function () {
            location.href = "${root}/products/post";
        });

        $("#searchBtn").click(function () {
            var word = $("#sword").val();
            if(word == "") {
                alert("모든 목록 조회!!!");
            }
            $("#searchform").attr("action", "${root}/products").submit();
        });

        $(".page-item").click(function() {
            $("#pg").val(($(this).attr("data-pg")));
            $("#pageform").attr("action", "${root}/guestbook/list").submit();
        });

        //file download
        $('.filedown').click(function() {
            alert("원본 :  " + $(this).attr('ofile') + "      실제 :  " + $(this).attr('sfile'));
            $(document).find('[name="sfolder"]').val($(this).attr('sfolder'));
            $(document).find('[name="ofile"]').val($(this).attr('ofile'));
            $(document).find('[name="sfile"]').val($(this).attr('sfile'));
            // Controller -> View (다운로드는 뜨면 안 됨)
            $('#downform').attr('action', '${root}/guestbook/download').attr('method', 'get').submit();
        });
    });
</script>
<form name="pageform" id="pageform" method="GET" action="">
    <input type="hidden" name="pg" id="pg" value="">
    <input type="hidden" name="key" id="key" value="${key}">
    <input type="hidden" name="word" id="word" value="${word}">
</form>
<%--form 으로 하면 한글 인코딩이 자동으로 된다--%>
<form id="downform">
    <input type="hidden" name="sfolder">
    <input type="hidden" name="ofile">
    <input type="hidden" name="sfile">
</form>
<div class="container text-center mt-3">
    <div class="col-lg-8 mx-auto">
        <%@ include file="/WEB-INF/views/include/confirm.jsp" %>
        <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">상품목록</mark></h2>
        <div class="m-3 text-right">
            <button type="button" id="mvRegisterBtn" class="btn btn-link">상품추가</button>
        </div>
        <div class="m-3 row justify-content-end">
            <form id="searchform" class="form-inline" method="get">
                <input type="hidden" name="pg" value="1">
                <select id="skey" name="key" class="form-control">
                    <option value="name"> 상품 이름
                </select>
                <input type="text" class="ml-1 form-control" id="sword" name="name" value="${name}">
                <button type="button" id="searchBtn" class="ml-1 btn btn-outline-primary">검색</button>
            </form>
        </div>
        <c:if test="${!empty articles}">
            <c:forEach var="product" items="${products}">
                <table class="table table-active text-left">
                    <tbody>
                    <tr class="table-info">
                        <td>작성자 : ${product.userName}</td>
                        <td class="text-right">작성일 : ${product.regTime}</td>
                    </tr>
                    <tr>
                        <td colspan="2" class="table-danger">
                            <strong>${product.articleNo}. ${product.subject}</strong>
                        </td>
                    </tr>
                    <tr>
                        <td class="p-4" colspan="2">
                                ${product.content}
                        </td>
                    </tr>
                        <%--파일이 있으면--%>
                    <c:if test="${!empty product.fileInfos}">
                        <tr>
                            <td colspan="2">
                                <ul>
                                        <%--루프 돌면서 실행--%>
                                    <c:forEach var="file" items="${product.fileInfos}">
                                    <li>${file.originFile} <a href="#" class="filedown" sfolder="${file.saveFolder}" sfile="${file.saveFile}" ofile="${file.originFile}">[다운로드]</a>
                                        </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${userinfo.userId eq product.userId}">
                        <tr>
                            <td colspan="2" class="text-right">
                                <a href="${root}/products/modify?isbn=${product.isbn}">수정</a>
                                <a href="${root}/products/delete?isbn=${product.isbn}">삭제</a>
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </c:forEach>
            <table class="text-center">
                <tr>
                    <td>${navigation.navigator}</td>
                </tr>
            </table>
        </c:if>
        <c:if test="${empty products}">
            <table class="table table-active text-center">
                <tr class="table-info">
                    <td>등록된 상품이 없습니다.</td>
                </tr>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>