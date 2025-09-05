<%@page import="windy.hospital.model.AmbulanceModel"%>
<%@page import="windy.hospital.model.SiteModel"%>
<%@page import="windy.hospital.model.RegionModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
ArrayList<RegionModel> listRegion = (ArrayList<RegionModel>) request.getAttribute("listRegion");
ArrayList<SiteModel> listSite = (ArrayList<SiteModel>) request.getAttribute("listSite");
ArrayList<AmbulanceModel> listAmbulance = (ArrayList<AmbulanceModel>) request.getAttribute("listAmbulance");
ArrayList<VolunteerceModel> listVolunteer = (ArrayList<VolunteerceModel>) request.getAttribute("listVolunteer");
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
    <div>
        <div>
			<jsp:include page="../top_menu.jsp"></jsp:include>
		</div>
		
        <div class="div_contents" style="display: flex;">
            <div style="width: 20%; height: 100%">
			<jsp:include page="../side_menu.jsp"></jsp:include>
            </div>
            <div style="width: 75%; height: 100%">
	            <div style="height: 43%; width: 100%; display: flex;">
	                <div class="content1_left_contents">
	
	                </div>
	
	                <div class="content1_center_contents">
	                    <div style="height: 30%; width: 100%;">
	                        <table class="tb_css center_tb_1">
	                            <colgroup>
	                                <col width="33%">
	                                <col width="33%">
	                                <col width="33%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th>입원</th>
										<th>누적진료</th>
										<th>누적사망</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>38/50</td>
										<td>10,500</td>
										<td>04</td>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	
	                    <div style="height: 20%; width: 100%;">
	                        <table class="tb_css center_tb_2">
	                            <colgroup>
	                                <col width="25%">
	                                <col width="25%">
	                                <col width="25%">
	                                <col width="25%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th>오늘입원</th>
										<th>오늘진료</th>
										<th>오늘후송</th>
	                                    <th>오늘사망</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>11</td>
										<td>500</td>
										<td>04</td>
	                                    <td>04</td>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	
	                    <div style="height: 50%; width: 100%;">
	                        <table class="tb_css center_tb_3">
	                            <colgroup>
	                                <col width="7%">
	                                <col width="7%">
	                                <col width="7%">
	                                <col width="7%">
	                                <col width="7%">
	                                <col width="7%">
	                                <col width="7%">
	                                <col width="7%">
	                                <col width="7%">
	                                <col width="7%">
	                                <col width="30%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th colspan="10">오늘입원</th>
										<th>누적후송</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
	                                    <td>2</td>
	                                    <td>3</td>
	                                    <td>4</td>
	                                    <td>5</td>
	                                    <td>6</td>
	                                    <td>7</td>
	                                    <td>8</td>
	                                    <td>9</td>
	                                    <td>10</td>
	                                    <td rowspan="2">21</td>
									</tr>
	                                <tr>
										<td style="height: 90px;"></td>
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	                </div>
	
	                <div class="content1_right_contents">
	                    <div style="height: 33.3%; width: 100%;">
	                        <table class="tb_css right_tb_1">
	                            <colgroup>
	                                <col width="100%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th>음압(S)</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>03/08</td>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	
	                    <div style="height: 33.3%; width: 100%;">
	                        <table class="tb_css right_tb_2">
	                            <colgroup>
	                                <col width="100%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th>관찰(A)</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>12/18</td>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	
	                    <div style="height: 33.3%; width: 100%;">
	                        <table class="tb_css right_tb_3">
	                            <colgroup>
	                                <col width="100%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th>일반(B)</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>21/24</td>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	                </div>
	            </div>
	
	            <div style="height: 7%; width: 100%; display: flex; align-items: center;">
	                <span style="margin-left: 20px; font-size: 30px; font-weight: bold; color: #496E73;">전라남도 완도군 신지면 신리 999-9</span>
	            </div>
	
	            <div style="height: 43%; width: 100%; display: flex;">
	                <div class="content2_left_contents">
	                    <table class="tb_css tb2_left">
	                        <colgroup>
	                            <col width="50%">
	                            <col width="50%">
	                        </colgroup>
	                        <thead>
	                            <tr>
	                                <th colspan="2">중앙통제실/지역</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <tr>
	                                <td>지휘본부</td>
	                                <td>061-000-0000</td>
	                            </tr>
	                            <tr>
	                                <td>재난본부</td>
	                                <td>061-000-0000</td>
	                            </tr>
	                            <tr>
	                                <td>지역보건소</td>
	                                <td>061-000-0000</td>
	                            </tr>
	                            <tr>
	                                <td>지역경찰서</td>
	                                <td>112</td>
	                            </tr>
	                            <tr>
	                                <td>지역소방서</td>
	                                <td>119</td>
	                            </tr>
	                            <tr>
	                                <td>지역방송국</td>
	                                <td>061-000-0000</td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
	
	                <div class="content2_center_contents">
	                    <table class="tb_css tb2_center">
	                        <colgroup>
	                            <col width="10%">
	                            <col width="15%">
	                            <col width="15%">
	                            <col width="30%">
	                            <col width="15%">
	                            <col width="15%">
	                        </colgroup>
	                        <thead>
	                            <tr>
	                                <th>NO</th>
	                                <th>책임자</th>
	                                <th>이름</th>
	                                <th>연락처</th>
	                                <th>소속</th>
	                                <th>비고</th>
	                            </tr>
	                            <tr>
	                                <td>01</td>
	                                <td>지휘본부장</td>
	                                <td>ㅇㅇㅇ</td>
	                                <td>010-0000-0000</td>
	                                <td></td>
	                                <td></td>
	                            </tr>
	                            <tr>
	                                <td>02</td>
	                                <td>공보관</td>
	                                <td>ㅇㅇㅇ</td>
	                                <td>010-0000-0000</td>
	                                <td></td>
	                                <td></td>
	                            </tr>
	                            <tr>
	                                <td>03</td>
	                                <td>시설관리</td>
	                                <td>ㅇㅇㅇ</td>
	                                <td>010-0000-0000</td>
	                                <td></td>
	                                <td></td>
	                            </tr>
	                            <tr>
	                                <td>04</td>
	                                <td>의료행정</td>
	                                <td>ㅇㅇㅇ</td>
	                                <td>010-0000-0000</td>
	                                <td></td>
	                                <td></td>
	                            </tr>
	                            <tr>
	                                <td>05</td>
	                                <td>의료</td>
	                                <td>ㅇㅇㅇ</td>
	                                <td>010-0000-0000</td>
	                                <td></td>
	                                <td></td>
	                            </tr>
	                            <tr>
	                                <td>06</td>
	                                <td>응급후송</td>
	                                <td>ㅇㅇㅇ</td>
	                                <td>010-0000-0000</td>
	                                <td></td>
	                                <td></td>
	                            </tr>
	                        </thead>
	                    </table>
	                </div>
	
	                <div class="content2_right_contents">
	                    <div style="width: 100%; height: 80%;">
	                        <table class="tb_css tb2_right">
	                            <colgroup>
	                                <col width="">
	                                <col width="">
	                                <col width="">
	                                <col width="">
	                                <col width="">
	                                <col width="">
	                                <col width="">
	                            </colgroup>
	                            <thead>
	                                <tr>
	                                    <th>분류</th>
	                                    <th>전체</th>
	                                    <th>사용량</th>
	                                    <th>잔여량</th>
	                                    <th>S소계</th>
	                                    <th>A소계</th>
	                                    <th>B소계</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                <tr>
	                                    <th>청수</th>
	                                    <td>3,500L</td>
	                                    <td>1,080L</td>
	                                    <td>2,420L</td>
	                                    <td>700L</td>
	                                    <td>200L</td>
	                                    <td>205L</td>
	                                </tr>
	                                <tr>
	                                    <th>폐수</th>
	                                    <td>3,000L</td>
	                                    <td>680L</td>
	                                    <td>2,400L</td>
	                                    <td>80L</td>
	                                    <td>89L</td>
	                                    <td>82L</td>
	                                </tr>
	                                <tr>
	                                    <th>전력</th>
	                                    <td>600kw</td>
	                                    <td>230kw</td>
	                                    <td>370kw</td>
	                                    <td>115kw</td>
	                                    <td>35kw</td>
	                                    <td>30kw</td>
	                                </tr>
	                            </tbody>
	                        </table>
	                    </div>
	
	                    <div style="width: 100%; height: 20%; display: flex;">
	                        <table class="tb_css_under">
	                            <colgroup>
	                                <col widtd="">
	                                <col widtd="">
	                                <col widtd="">
	                                <col widtd="">
	                                <col widtd="">
	                                <col widtd="">
	                            </colgroup>
	                            <tbody>
	                                <tr>
	                                    <td>의사:6</td>
	                                    <td>간호사:12</td>
	                                    <td>행정:6</td>
	                                    <td>구급대:9</td>
	                                    <td>봉사:14</td>
	                                    <td>합계:47</td>
	                                </tr>
	                            </tbody>
	                        </table>
	                    </div>
	                </div>
	            </div>
	
	            <div style="height: 7%; width: 100%; display: flex; justify-content: center; align-items: center;">
	                <div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center; background-color: #E2EBED; padding-left: 50px; margin-top: 20px;">
	                    <table class="tb_css_footer">
	                        <colgroup>
	                            <col width="">
	                            <col width="">
	                            <col width="">
	                            <col width="">
	                            <col width="">
	                            <col width="">
	                            <col width="">
	                        </colgroup>
	                        <tbody>
	                            <tr>
	                                <td>거점병원1:00/10</td>
	                                <td>거점병원2:00/10</td>
	                                <td>거점병원3:00/10</td>
	                                <td>거점병원4:00/10</td>
	                                <td>거점병원5:00/10</td>
	                                <td>거점병원6:00/10</td>
	                                <td>거점병원7:00/10</td>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
            </div>
        </div>
    </div>
</body>
</html>