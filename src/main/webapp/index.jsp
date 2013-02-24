<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            if (!!window.EventSource) {
                var source = new EventSource('api/games/events');

                source.addEventListener('new', function(e) {
                    var log = document.getElementById("log");
                    log.innerHTML += ("new> " + e.data + "<br>");
                }, false);

                source.addEventListener('change', function(e) {
                    var log = document.getElementById("log");
                    log.innerHTML += ("change> " + e.data + "<br>");
                }, false);

            } else {
                alert("SSE is ot supported");
            }
        </script>
    </head>
    <body>
        <h1>The Antwerp project</h1>
        <div id="log">

        </div>
    </body>
</html>
