<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<script type="text/javascript">
    $(function () {
        $("#gotoPage").click(function (){
            var gotoPageNo = $("#pn_input").val();
            window.location.href = "${requestScope.pageList.url}&pageNo="+gotoPageNo;
        });
    });
</script>

<div id="page_nav">
    <!-- 页码大于首页才显示 -->
    <c:if test="${requestScope.pageList.pageNo > 1}">
        <a href="${requestScope.pageList.url}&pageNo=1">首页</a>
    </c:if>

    <c:if test="${requestScope.pageList.hasPre}">
        <a href="${requestScope.pageList.url}&pageNo=${requestScope.pageList.pageNo-1}">上一页</a>
    </c:if>


    <!-- 页码输出的开始 -->
    <c:choose>
        <%-- 当页数小于5时 --%>
        <c:when test="${requestScope.pageList.allPage <= 5}">
            <c:set var="begin" value="1" scope="page"></c:set>
            <c:set var="end" value="${requestScope.pageList.allPage}" scope="page"></c:set>
        </c:when>
        <%-- 当页数大于5时 --%>
        <c:when test="${requestScope.pageList.allPage > 5}">
            <c:choose>
                <%-- 当页码小于3 --%>
                <c:when test="${requestScope.pageList.pageNo <= 3}">
                    <c:set var="begin" value="1" scope="page"></c:set>
                    <c:set var="end" value="5" scope="page"></c:set>
                </c:when>

                <%-- 当页码大于等于页数-2时 --%>
                <c:when test="${requestScope.pageList.pageNo >= requestScope.pageList.allPage-2}">
                    <c:set var="begin" value="${requestScope.pageList.allPage-4}" scope="page"></c:set>
                    <c:set var="end" value="${requestScope.pageList.allPage}" scope="page"></c:set>
                </c:when>

                <%-- 当页码大于3，小于页数减2时--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.pageList.pageNo-2}" scope="page"></c:set>
                    <c:set var="end" value="${requestScope.pageList.pageNo+2}" scope="page"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>


    <c:forEach begin="${pageScope.begin}" var="i" end="${pageScope.end}">
        <!-- 是当前页的话就不加链接 -->
        <c:if test="${i == requestScope.pageList.pageNo}">
            【${requestScope.pageList.pageNo}】
        </c:if>
        <!-- 不是当前页的话就加链接 -->
        <c:if test="${i != requestScope.pageList.pageNo}">
            <a href="${requestScope.pageList.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:if test="${requestScope.pageList.hasNext}">
        <a href="${requestScope.pageList.url}&pageNo=${requestScope.pageList.pageNo+1}">下一页</a>
    </c:if>

    <!-- 页码小于末页才显示 -->
    <c:if test="${requestScope.pageList.pageNo < requestScope.pageList.allPage}">
        <a href="${requestScope.pageList.url}&pageNo=${requestScope.pageList.allPage}">末页</a>
    </c:if>

    共${requestScope.pageList.allPage}页，
    ${requestScope.pageList.allCount}条记录 &nbsp;&nbsp;
    到第<input value="${requestScope.pageList.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="gotoPage">
</div>