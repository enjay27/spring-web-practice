<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#registerBtn").click(function () {
            if (!$("#name").val()) {
                alert("제목 입력!!!!");
                return;
            } else if (!$("#isbn").val()) {
                alert("번호 입력!!!!");
                return;
            } else if (!$("#price").val()) {
                alert("가격 입력!!!!");
                return;
            } else {
                $("#writeform").attr("action", "${root}/products").submit();
            }
        });
    });
</script>

<div class="container text-center mt-3">
    <div class="col-lg-8 mx-auto">
        <%@ include file="/WEB-INF/views/include/confirm.jsp" %>
        <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">상품 등록</mark></h2>
        <form id="writeform" class="text-left mb-3" method="post" enctype="multipart/form-data" action="">
            <div class="form-group">
                <label for="name">상품 이름:</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="제목...">
            </div>
            <div class="form-group">
                <label for="isbn">상품 번호:</label>
                <input type="text" class="form-control" id="isbn" name="isbn"/>
            </div>
            <div class="form-group">
                <label for="price">상품 가격:</label>
                <input type="number" class="form-control" id="price" name="price"/>
            </div>
            <div class="form-group">
                <label for="explanation">내용:</label>
                <textarea class="form-control" rows="15" id="explanation" name="explanation"></textarea>
            </div>
            <div class="form-group" align="left">
                <label for="upfile">파일:</label>
                <input type="file" class="form-control-file border" name="upfile" multiple="multiple">
            </div>
            <div class="text-center">
                <button type="button" id="registerBtn" class="btn btn-outline-primary">글작성</button>
                <button type="reset" class="btn btn-outline-danger">초기화</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>