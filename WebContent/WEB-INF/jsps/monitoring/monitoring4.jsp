<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<jsp:include page="../top_menu.jsp"></jsp:include>
		</div>
		<div style="display: flex;">
			<div>
				<jsp:include page="../side_menu.jsp"></jsp:include>
			</div>
			<div class="wrapper_contents" >
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
									<td class="">0.0 V</td>
								</tr>
								<tr>
									<td>Current</td>
									<td>0.00 A</td>
								</tr>
								<tr>
									<td>Frequency</td>
									<td>0.00 Hz</td>
								</tr>
								<tr>
									<td>Active Power</td>
									<td>0.0 W</td>
								</tr>
								<tr>
									<td>Reactive Power</td>
									<td>0.0 VAr</td>
								</tr>
								<tr>
									<td>Power factor</td>
									<td>0.00</td>
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
									<td>Pa</td>
								</tr>
								<tr>
									<td>Current Pressure</td>
									<td>Pa</td>
								</tr>
								<tr>
									<td>Operation State</td>
									<td></td>
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
									<td>0 V</td>
								</tr>
								<tr>
									<td>Battery current</td>
									<td>0.0 A</td>
								</tr>
								<tr>
									<td>Output Frequency</td>
									<td>0.0 Hz</td>
								</tr>
								<tr>
									<td>Operation</td>
									<td style="text-align: center;">OFF</td>
								</tr>
								<tr>
									<td>Low Battery</td>
									<td style="text-align: center;">OFF</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="wrapper_right_contents_7">
					<div style="border: 1px solid #74ADBD; height: 80%">
					
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
										<td>-24.6%</td>
									</tr>
									<tr>
										<td>Clean Water</td>
										<td>-24.6%</td>
									</tr>
									<tr>
										<td>Waste Water</td>
										<td>-24.6%</td>
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
										<td>0.0 ℃</td>
										<td>℃</td>
										<td>℃</td>
										<td>℃</td>
									</tr>
									<tr>
										<th>Humidity</th>
										<td>%</td>
										<td>%</td>
										<td>%</td>
										<td>%</td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<div style="width: 10%; height: 100%; display: block;">
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