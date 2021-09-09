<%--
  Created by IntelliJ IDEA.
  User: 朱湘川
  Date: 2021/9/7
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../../static/css/style.css" type="text/css" >
    <meta charset="UTF-8">
    <title>我的订单详情</title>
    <style type="text-css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="../..atic/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@include file="/pages/common/login_sucess_menu.jsp" %>
</div>
<div id="main">
    <table id="cartList">
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>总价</td>
        </tr>
    </table>
</div>
<script>
    window.onload = () => {
        const orderId=window.location.href.split("?")[1].split("=")[1];
        const dataList = document.getElementById("cartList");
        let data = [];
        let total = 0;
        const url = window.location.href;
        const xhr = new XMLHttpRequest();
        xhr.open('Get', "http://47.100.10.244:8080/BookStore/manager/orderServlet?action=showOrderDetail&orderId="+orderId);
        xhr.send();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    data = JSON.parse(xhr.response);
                    data.forEach(item => {
                        const element = document.createElement("tr");
                        const name = document.createElement("td");
                        const count = document.createElement("td");
                        const price = document.createElement("td");
                        const total_Price = document.createElement("td");
                        name.innerHTML = item.name;
                        count.innerHTML = item.count;
                        price.innerHTML = item.price;
                        total_Price.innerHTML = item.total_Price;
                        total += item.total_Price;
                        element.appendChild(name);
                        element.appendChild(count);
                        element.appendChild(price);
                        element.appendChild(total_Price);
                        dataList.appendChild(element);
                    })
                    const totalElement = document.createElement("tr");
                    const abc=document.createElement("td");
                    abc.innerHTML=total;
                    totalElement.appendChild(abc);
                    dataList.appendChild(totalElement);
                } else {
                    console.log(xhr.error);
                }
            }
        }
    }
</script>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>

