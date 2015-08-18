<%-- 
    Document   : login.jsp
    Created on : August 17, 2015, 09:19:07 AM
    Author     : Aaron
    Description: Login form
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
        <meta http-equiv="refresh" content="900" >
        <meta http-equiv="X-UA-Compatible" content="IE=11; IE=10; IE=9; IE=8; IE=7; IE=EDGE" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="PRAGMA" content="NO-CACHE" />
        <meta name="CACHE-CONTROL" content="NO-STORE" />
        <meta name="COPYRIGHT" content="&amp;copy; 2007-2019 RHA" />
        <meta name="EXPIRES" content="Mon, 25 Aug 1977 11:12:01 GMT" />
        <meta name="ROBOTS" content="NONE" />
        <meta name="GOOGLEBOT" content="NOARCHIVE" />
        
        <script type="text/javascript" src="/rha/javascript/login.js"></script>
        <link rel="stylesheet" type="text/css" href="/rha/styles/login.css"/>
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <title>RPG Directory - Sign In</title>
    </head>

    <body>
        <header>
            <nav id="mainNavigation" role="navigation" style="font-size:18px;color:#336699;padding-left:6px;font-weight:bold;">
                RPG Directory
            </nav>
        </header>
        <div id="content" class="section no-nav-content margin-bottom-80">
            <form id='login_form' onsubmit='return login(this)' autocomplete="off"
                  enctype="application/x-www-form-urlencoded" 
                  action="j_spring_security_check" method="post">
                <%--
                <px:csrf-token />
                --%>
                <div class='module login-module'>
                    <h3>Sign In to Directory</h3>
                    <ul class='vertical-form login-form module-inner'>
                        <li>
                            <label for="j_username" style="width:70px;">User Name:</label>
                            <input id="j_username" name="j_username" size=20" maxlength="30" type="text"/>
                        </li>
                        <li>
                            <label for="j_password" style="width:70px;">Password:</label>
                            <input id="j_password" name="j_password" size="20" maxlength="30" type="password" autocomplete="off" />
                        </li>
                        <li>
                            <input class="link-button link-button-primary" title="Sign In" type="submit" value="Sign In"/>
                        </li>
                    </ul>
                </div>
            </form>
            <p class="alert"><c:out value="${message}" /> </p>
        </div>
    </body>
</html>