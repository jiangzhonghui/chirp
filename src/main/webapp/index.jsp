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
	<div class="main">
		<h1>This is the Chirp REST API!</h1>
		<p>Chirp is a trivial, Twitter-like, REST API for messaging
			tweets!</p>

		<h2>API Documentation</h2>
		<p>
			Calls to Chirp must provide an authentication token via a custom HTTP
			header, like the following one: <br />
			<code> Chirp-Token: fc5e1e0c-60fd-aa11-8d5b-754980f16afb </code>
		</p>
		<p>Any call that either does not include the token or provides an
			invalid one will return the HTTP error 401 (unauthorized).</p>
		<p>If call is successful, Chirp will reply with a proper JSON
			response.</p>
		<p>Next table summarizes the REST interfaces of Chirp.</p>
		<table border="1">
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
					<td><p>
							Gets all of the chirps related to the specified
							<code>{user}</code>
							user, including:
						</p>
						<ul>
							<li>all chirps from the user himself</li>
							<li>all chirps from people user is following</li>
						</ul>
						<p>Chirps are sorted by creation date, most recent posts are
							returned first.</p>
						<p>Returns the HTTP error code 404 (not found) if specified
							user is not found.</p></td>
				</tr>
				<tr>
					<td class="url"><code>/api/{user}/chirps?search={keyword}</code></td>
					<td class="method">GET</td>
					<td><p>
							Gets all of the chirps related to the specified
							<code>{user}</code>
							user whose content includes the specified
							<code>{keyword}</code>
							keyword.
						</p>
						<p>Returned posts will include:</p>
						<ul>
							<li>all chirps from the user himself</li>
							<li>all chirps from people user is following</li>
						</ul>
						<p>Chirps are sorted by creation date, most recent posts are
							returned first.</p>
						<p>Returns the HTTP error code 404 (not found) if specified
							user is not found.</p></td>
				</tr>
				<tr>
					<td class="url"><code>/api/{user}/people</code></td>
					<td class="method">GET</td>
					<td><p>
							Gets the list of people the given
							<code>{user}</code>
							is following along with those people that follow him.
						</p>
						<p>Returns the HTTP error code 404 (not found) if specified
							user is not found.</p></td>
				</tr>
				<tr>
					<td class="url"><code>/api/{user}/follow</code></td>
					<td class="method">PUT</td>
					<td><p>
							Makes the authenticated user (identified by the input token)
							follow the specified
							<code>{user}</code>
							user.

						</p>
						<p>Always returns true if operation goes fine.</p>
						<p>Returns the HTTP error code 404 (not found) if specified
							user is not found.</p></td>
				</tr>
				<tr>
					<td class="url"><code>/api/{user}/unfollow</code></td>
					<td class="method">PUT</td>
					<td><p>
							Stops the authenticated user (identified by the input token) from
							following the specified
							<code>{user}</code>
							user.

						</p>
						<p>Always returns true if operation goes fine.</p>
						<p>Returns the HTTP error code 404 (not found) if specified
							user is not found.</p></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
