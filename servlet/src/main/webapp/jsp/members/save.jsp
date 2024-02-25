<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response 사용 가능 문법상 지원이 됨.
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("save-result.jsp");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));        // 응답파일 형태가 늘 문자타입이기에 Integer로 변환해줘야함.

    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);
%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
