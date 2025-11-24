<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Redirecting...</title>
	</head>
	<body>
		<form id="autoForm" method="post" action="tarefa">
			<input type="hidden" name="action" value="list" />
			<noscript>
				<p>Se o redirecionamento automático não funcionar, <button type="submit">clique aqui</button>.</p>
			</noscript>
		</form>
		<script>
			// Submit POST to TarefaController with parameter action=1
			(function(){
				var f = document.getElementById('autoForm');
				if(f) { f.submit(); }
			})();
		</script>
	</body>
</html>