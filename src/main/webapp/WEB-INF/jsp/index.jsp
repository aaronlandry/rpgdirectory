<%-- 
    Document   : index
    Created on : August 17, 2015, 10:54:51 AM
    Author     : Aaron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-ng-app="gameApp">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=11; IE=10; IE=9; IE=8; IE=7; IE=EDGE" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="PRAGMA" content="NO-CACHE" />
        <meta name="CACHE-CONTROL" content="NO-STORE" />
        <meta name="COPYRIGHT" content="&amp;copy; 2007-2019 RHA RPG Directory" />
        <meta name="EXPIRES" content="Mon, 25 Aug 1977 11:12:01 GMT" />
        <meta name="ROBOTS" content="NONE" />
        <meta name="GOOGLEBOT" content="NOARCHIVE" />
        <%-- DISABLED FOR SIMPLICITY
        <meta name="_csrf" content="${_csrf.token}"/>
        <!-- default header name is X-CSRF-TOKEN -->
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        --%>
        
        <%--
        <link rel="stylesheet" type="text/css" href="/rha/styles/main.css"/>
        <link rel="stylesheet" type="text/css" href="/rha/styles/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/rha/styles/app.css"/>
        
        <script type="text/javascript" src="/rha/javascript/lib/angular.min.js"></script>
        <script type="text/javascript" src="/rha/javascript/app.js"></script>
        <script type="text/javascript" src="/rha/javascript/controllers.js"></script>
        <script type="text/javascript" src="/rha/javascript/services.js"></script>
        <script type="text/javascript" src="/rha/javascript/lib/angular-ui-router.min.js"></script>
        <script type="text/javascript" src="/rha/javascript/lib/angular-resource.min.js"></script>
        --%>
        
        <script type="text/javascript" src="/rha/javascript/compressed-min.js"></script>
        <link rel="stylesheet" type="text/css" href="/rha/styles/compressed-min.css"/>
        
        
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <title>rha Glossary</title>
    </head>
    
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" ui-sref="games">The Game Directory</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a ui-sref="games">Home</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row top-buffer">
                <div class="col-xs-8 col-xs-offset-2">
                    <div ui-view></div>
                </div>
            </div>
        </div>

    </body>
</html>