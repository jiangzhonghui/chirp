<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Chirp API</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="css/normalize.min.css">
<link rel="stylesheet" href="css/main.css">

<script src="js/vendor/modernizr-2.6.2.min.js"></script>
</head>
<body>

	<h1>This is the Chirp REST API!</h1>
	<p>Chirp is a trivial, Twitter-like, REST API for messaging tweets!</p>

	<h2>API Documentation</h2>
	<table>
		<thead>
			<tr>
				<th>URL</th>
				<th>Method</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="url"><code>/api/{user}/chirps</code></td>
				<td class="method">GET</td>
				<td>Gets all of the tweet of the given <code>{user}</code> user
				</td>
			</tr>
			<tr>
				<td class="url"><code>/api/{user}/people</code></td>
				<td class="method">GET</td>
				<td>Gets the list of people the given <code>{user}</code> is
					following along with those people that follow him.
				</td>
			</tr>
		</tbody>
	</table>

</body>
</html>
