<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jspf"%>

<article>

	<h1 class="text-center p-5" style="font-family:Algerian">Home Page</h1>
	<div class="row">
		<div class="col">
			<!-- Dummy tag for maintaining structure of cards -->
		</div>
		<div class="col">
			<div class="card bg-light text-center" style="width: 18rem;">
				<img class="card-img-top" src="/images/docmr.jpg" alt="img medical representative" width="290" height="147">
				<div class="card-body">
					<h5 class="card-title">Medical Representatives</h5>
					<p class="card-text">Click here to view medical representatives
						schedule for doctors visit</p>

					<a class="btn btn-primary" href="/user/schedule">Medical
						Representative</a>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card bg-light text-center" style="width: 18rem;">
				<img class="card-img-top" src="/images/img.jpeg" alt="img medicine stock" width= "286" height="170">
				<div class="card-body">
					<h5 class="card-title">Medicine Stock</h5>
					<p class="card-text">Click here to view detailed list of
						medicines present in stock</p>

					<a class="btn btn-primary" href="/user/medicineStock">Medicine
						Stock</a>
				</div>
			</div>
		</div>

		<div class="col">
			<!-- Dummy tag for maintaining structure of cards -->
		</div>
	</div>

</article>




<%@ include file="footer.jspf"%>