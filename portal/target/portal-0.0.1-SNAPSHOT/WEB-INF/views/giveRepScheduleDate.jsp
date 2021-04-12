<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jspf"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<article>

	<h1 class="text-center p-5" style="font-family:Algerian">Medical Representatives Schedule</h1>

	<div class="row">
		<div class="col">
			<!-- Dummy tag for maintaining structure of cards -->
		</div>
		<div class="col card bg-light text-center" style="width: 18rem;">
			<img class="card-img-top"
				src="https://static8.depositphotos.com/1229718/1053/i/950/depositphotos_10534576-stock-photo-calendar-date.jpg"
				alt="img planner and calender" width="100" height="300">
			<div class="card-body">
				<h5 class="card-title">Select Date</h5>
				<p class="card-text">To view the schedule of the representatives
				</p>


				<form action="/user/createSchedule" method="post">

					<div style="color: red; margin: 20px">
						<c:choose>
							<c:when test="${errorMessage}">
							Please Enter today's / upcoming date
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>

					</div>


					<div style="color: red"></div>
					<div>
						<div>
							<i style="font-size: 24px; margin: 0 0 5px 0;" class="fa">&#xf073;
							</i> <span style="padding: 5px;">Date of meeting</span> <input type="date" name="scheduleStartDate"
								value="" required="true">
						</div>
					</div>
					<div>
						<div>
							<input type="submit" class="btn btn-info m-3" name="submit"
								value="View schedule">
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col">
			<!-- Dummy tag for maintaining structure of cards -->
		</div>
	</div>

</article>

</html>
<%@ include file="footer.jspf"%>
