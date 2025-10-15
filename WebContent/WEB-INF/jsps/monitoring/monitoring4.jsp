<%@page import="windy.hospital.model.VehicleModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	VehicleModel vehicle = (VehicleModel) request.getAttribute("vehicle");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="js/jquery-3.7.1.min.js"></script>
<script>

function side() {
	var sideType = document.getElementById('side_type').value;
	
	if(sideType=='on') {
		document.getElementById('side_type').value = 'off';
		document.getElementById('side_menu').style.display = 'none';
	} else {
		document.getElementById('side_type').value = 'on';
		document.getElementById('side_menu').style.display = '';
	}
}

</script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<jsp:include page="../top_menu.jsp"></jsp:include>
		</div>
		<div style="display: flex;">
			<div style="width: 15%; height: 94vh; z-index: 1000;" id="side_menu">
				<jsp:include page="../side_menu.jsp"></jsp:include>
			</div>
			<div class="wrapper_contents" style="width: 94%; height: 93%; padding-left: 3%; position: absolute;">
				<div class="wrapper_left_contents_3">
					<div style="width: 100%; height: 40%;">
						<table class="tb_basic tb_car" style="width: 100%;">
							<colgroup>
								<col width="60%">
								<col width="40%">
							</colgroup>
							<thead>
								<tr>
									<th colspan="2">Power State</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Voltage</td>
									<td class=""><%=vehicle.getVoltage() %> V</td>
								</tr>
								<tr>
									<td>Current</td>
									<td><%=vehicle.getCurrent() %> A</td>
								</tr>
								<tr>
									<td>Frequency</td>
									<td><%=vehicle.getFrequency() %> Hz</td>
								</tr>
								<tr>
									<td>Active Power</td>
									<td><%=vehicle.getActivePower() %> W</td>
								</tr>
								<tr>
									<td>Reactive Power</td>
									<td><%=vehicle.getReactivePower() %> VAr</td>
								</tr>
								<tr>
									<td>Power factor</td>
									<td><%=vehicle.getPowerFactor() %></td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<div style="width: 100%; height: calc(25% - 10px); padding-top: 10px;">
						<table class="tb_basic tb_car" style="width: 100%;">
							<colgroup>
								<col width="60%">
								<col width="40%">
							</colgroup>
							<thead>
								<tr>
									<th colspan="2">MPD</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Input Pressure</td>
									<td><%=vehicle.getMpdInputp() %></td>
								</tr>
								<tr>
									<td>Current Pressure</td>
									<td><%=vehicle.getMpdCurrrenttp() %></td>
								</tr>
								<tr>
									<td>Operation State</td>
									<td><%=vehicle.getMpdStatus() %></td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<div style="width: 100%; height: calc(35% - 10px); padding-top: 10px;">
						<table class="tb_basic tb_car" style="width: 100%;">
							<colgroup>
								<col width="60%">
								<col width="40%">
							</colgroup>
							<thead>
								<tr>
									<th colspan="2">UPS</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Battery voltage</td>
									<td><%=vehicle.getUpsVoltage() %> V</td>
								</tr>
								<tr>
									<td>Battery current</td>
									<td><%=vehicle.getUpsCurrent() %> A</td>
								</tr>
								<tr>
									<td>Output Frequency</td>
									<td><%=vehicle.getUpsFrequency() %> Hz</td>
								</tr>
								<tr>
									<td>Operation</td>
									<td style="text-align: center;"><%=vehicle.getUpsStatus() %></td>
								</tr>
								<tr>
									<td>Low Battery</td>
									<td style="text-align: center;"><%=vehicle.getUpsLowBattery() %></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="wrapper_right_contents_7">
					<div style="border: 1px solid #74ADBD; height: 80%">
						
						<img alt="" src="images/car.png" style="width: 100%; height: 100%;">
					</div>
					
					<div style="height: calc(20% - 10px); display: flex; padding-top: 10px;">
						<div style="width: calc(30% - 10px); height: 100%; padding-right: 10px;">
							<table class="tb_basic tb_tank">
								<colgroup>
									<col width="60%">
									<col width="40%">
								</colgroup>
								<thead>
									<tr>
										<th colspan="2">Tank State</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Fuel</td>
										<td><%=vehicle.getFuel() %> %</td>
									</tr>
									<tr>
										<td>Clean Water</td>
										<td><%=vehicle.getCleanWater() %> %</td>
									</tr>
									<tr>
										<td>Waste Water</td>
										<td><%=vehicle.getWasteWater() %> %</td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<div style="width: calc(60% - 10px); height: 100%; padding-right: 10px;">
							<table class="tb_basic tb_tank">
								<colgroup>
									<col width="20%">
									<col width="20%">
									<col width="20%">
									<col width="20%">
									<col width="20%">
								</colgroup>
								<thead>
									<tr>
										<th>Room</th>
										<th>장비실</th>
										<th>증폭실</th>
										<th>추출실</th>
										<th>통제실</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th>Temperature</th>
										<td><%=vehicle.getTmp1() %> ℃</td>
										<td><%=vehicle.getTmp2() %> ℃</td>
										<td><%=vehicle.getTmp3() %> ℃</td>
										<td><%=vehicle.getTmp4() %> ℃</td>
									</tr>
									<tr>
										<th>Humidity</th>
										<td><%=vehicle.getHue1() %> %</td>
										<td><%=vehicle.getHue2() %> %</td>
										<td><%=vehicle.getHue3() %> %</td>
										<td><%=vehicle.getHue4() %> %</td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<div style="width: 10%; height: 100%; display: none;">
							<div style="width: 100%; height: 33.3%; text-align: center; display: flex; vertical-align: middel; justify-content: center; align-items: center;">
								<button class="btn_car">GEN</button>
							</div>
							<div style="width: 100%; height: 33.3%; text-align: center; display: flex; vertical-align: middel; justify-content: center; align-items: center;">
								<button class="btn_car">UPS</button>
							</div>
							<div style="width: 100%; height: 33.3%; text-align: center; display: flex; vertical-align: middel; justify-content: center; align-items: center;">
								<button class="btn_car">MPD</button>
							</div> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>