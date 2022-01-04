<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css"> 
    .image {
    position: absolute;
	margin-left: -10px;
	margin-top: 50px;
	margin-bottom: -40px
    }
 
   
    }
  </style>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<!-- Site Properties -->
<title>Homepage - Semantic</title>


<script>
	function goMap() {
		document.location.href = "map";
	}
</script>
</head>
<body>
	<div id='content' class="ui text container">
		
		<img class="ui image" src="/img/main.png">
		<div class="ui primary huge magenta button" onclick="goMap()">
			Let's Video Chat <i class="right arrow icon"></i>
		</div>
	</div>

	<div class="ui vertical stripe segment">
		<div class="ui middle aligned stackable grid container">
			<div class="row">
				<div class="eight wide column">
					<h3 class="ui header">We Help Companies and Companions</h3>
					<p>We can give your company superpowers to do things that they
						never thought possible. Let us delight your customers and empower
						your needs...through pure data analytics.</p>
					<h3 class="ui header">We Make Bananas That Can Dance</h3>
					<p>Yes that's right, you thought it was the stuff of dreams,
						but even bananas can be bioengineered.</p>
				</div>
				<div class="six wide right floated column">
					<img style="float: right;" src="/img/liquor.jpg"
						class="ui large bordered rounded image">
				</div>

			</div>
			<div class="row">
				<div class="center aligned column">
					<a class="ui huge button">Check Them Out</a>
				</div>
			</div>
		</div>
	</div>

	<div class="ui vertical stripe quote segment">
		<div class="ui equal width stackable internally celled grid">
			<div class="center aligned row">
				<div class="column">
					<h3>"What a Company"</h3>
					<p>That is what they all say about us</p>
				</div>
				<div class="column">
					<h3>"I shouldn't have gone with their competitor."</h3>
					<p>

						<b>Nan</b> Chief Fun Officer Acme Toys
					</p>
				</div>
			</div>
		</div>
	</div>

	<div class="ui vertical stripe segment">
		<div class="ui text container">
			<h3 class="ui header">Breaking The Grid, Grabs Your Attention</h3>
			<p>Instead of focusing on content creation and hard work, we have
				learned how to master the art of doing nothing by providing massive
				amounts of whitespace and generic content that can seem massive,
				monolithic and worth your attention.</p>
			<a class="ui large button">Read More</a>
			<h4 class="ui horizontal header divider">
				<a href="#">Case Studies</a>
			</h4>
			<h3 class="ui header">Did We Tell You About Our Bananas?</h3>
			<p>Yes I know you probably disregarded the earlier boasts as
				non-sequitur filler content, but its really true. It took years of
				gene splicing and combinatory DNA research, but our bananas can
				really dance.</p>
			<a class="ui large button">I'm Still Quite Interested</a>
		</div>
	</div>


	<div class="ui inverted vertical footer segment">
		<div class="ui container">
			<div
				class="ui stackable inverted divided equal height stackable grid">
				<div class="three wide column">
					<h4 class="ui inverted header">About</h4>
					<div class="ui inverted link list">
						<a href="#" class="item">Sitemap</a> <a href="#" class="item">Contact
							Us</a> <a href="#" class="item">Religious Ceremonies</a> <a href="#"
							class="item">Gazebo Plans</a>
					</div>
				</div>
				<div class="three wide column">
					<h4 class="ui inverted header">Services</h4>
					<div class="ui inverted link list">
						<a href="#" class="item">Banana Pre-Order</a> <a href="#"
							class="item">DNA FAQ</a> <a href="#" class="item">How To
							Access</a> <a href="#" class="item">Favorite X-Men</a>
					</div>
				</div>
				<div class="seven wide column">
					<h4 class="ui inverted header">Footer Header</h4>
					<p>Extra space for a call to action inside the footer that
						could help re-engage users.</p>
				</div>
			</div>
		</div>
	</div>

</body>

</html>