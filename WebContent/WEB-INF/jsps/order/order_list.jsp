<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="windy.hospital.model.SuppliesModel"%>
<%@page import="windy.hospital.model.OrderModel"%>
<%@page import="windy.hospital.model.MedicineModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Calendar calendar = Calendar.getInstance();
Date day = calendar.getTime();

SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.KOREAN);

ArrayList<OrderModel> listOrder = (ArrayList<OrderModel>) request.getAttribute("listOrder");

ArrayList<MedicineModel> listMedicine = (ArrayList<MedicineModel>) request.getAttribute("listMedicine");
ArrayList<SuppliesModel> listSupplies = (ArrayList<SuppliesModel>) request.getAttribute("listSupplies");

String searchName = (String) request.getAttribute("name");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">

function popOpen(num, classPrefix, no) {
	var modalBg = $(num);
	var modalPop = $(classPrefix);

	if(num == '.modal-bg2'){
		getOrder(no);

	} else if(num == '.modal-bg3') {
		var suppliesNo = document.getElementById('supplies_no_'+no).value;
		var medicineNo = document.getElementById('medicine_no_'+no).value;
		var orderNo = document.getElementById('order_no_'+no).value;
		var name = document.getElementById('name_'+no).value;
		document.getElementById('supplies_no').value = suppliesNo;
		document.getElementById('medicine_no').value = medicineNo;
		document.getElementById('in_out_order_no').value = orderNo;
		document.getElementById('in_out_name').innerHTML = name;
		
	}

	modalBg.show();
	modalPop.show();
}

function popClose(num, classPrefix) {
	var modalBg = $(num);
	var modalPop = $(classPrefix);

	modalBg.hide();
	modalPop.hide();
}

function changeList() {
	var option = $("input[name='add_type']:checked").val();
	
	if(option=='medicine') {
		document.getElementById('add_medicine_no').style.display = "";
		document.getElementById('add_supplies_no').style.display = "none";
	} else {
		document.getElementById('add_medicine_no').style.display = "none";
		document.getElementById('add_supplies_no').style.display = "";
	}
}

function goSearch() {
	var name = document.getElementById('search_name').value;
	
	location.href="order.windy?menu=list&name="+name;
}

function addOrder() {
	var option = $("input[name='type']:checked").val();
	
	var suppliesNo = -1;
	var medicineNo = -1;
	
	if(option == 'medicine') {
		medicineNo = document.getElementById('add_medicine_no').value;
	} else {
		suppliesNo = document.getElementById('add_supplies_no').value;
	}
	
	var date = document.getElementById('add_order_date').value;
	var amount = document.getElementById('add_order_amount').value;
	var note = document.getElementById('add_order_note').value;
	
	var param = "&medicine_no="+medicineNo+"&supplies_no="+suppliesNo+"&date="+date+"&amount="+amount+"&note="+note;
		
	$.ajax({
		type: "post", 
		url: "order.windy?mode=add", 
		data: param,
		async: false, 
		dataType: 'text', 
		error: ajaxFailed,
		success: function(data, textStatus) {
			
			alert("등록되었습니다.");
			location.reload(); 
		}
	});
	
}

function goModify() {

	var no = document.getElementById('modify_no').value;
	var date = document.getElementById('modify_order_date').value;
	var amount = document.getElementById('modify_order_amount').value;
	var note = document.getElementById('modify_order_note').value;
	
	var param = "&no="+no+"&date="+date+"&amount="+amount+"&note="+note;
		
	$.ajax({
		type: "post", 
		url: "order.windy?mode=update", 
		data: param,
		async: false, 
		dataType: 'text', 
		error: ajaxFailed,
		success: function(data, textStatus) {
			
			alert("수정되었습니다.");
			location.reload(); 
		}
	});
	
}

function goDelete(no) {
		
	var param = "&no="+no;
	
	$.ajax({
		type: "POST",
		url: "medicine.windy?mode=delete", 
		data: param,
		error: ajaxFailed,
		success: function(ret) {

			alert("비활성화되었습니다.");
			location.reload(); 
		}
	});
	
}

function addInOut() {

	var option = $("input[name='add_type']:checked").val();
	
	var no = document.getElementById('in_out_order_no').value;
	var suppliesNo = document.getElementById('supplies_no').value;
	var medicineNo = document.getElementById('medicine_no').value;
	
	if(option=='medicine') {
		suppliesNo = -1;
	} else {
		medicineNo = -1;
	}
	
	var date = document.getElementById('in_out_date').value;
	var amount = document.getElementById('in_out_amount').value;
	var note = document.getElementById('in_out_note').value;
	
	var param = "&no="+no+"&supplies_no="+suppliesNo+"&medicine_no="+medicineNo+"&date="+date+"&amount="+amount+"&note="+note;
		
	$.ajax({
		type: "post", 
		url: "order.windy?mode=add_in_out", 
		data: param,
		async: false, 
		dataType: 'text', 
		error: ajaxFailed,
		success: function(data, textStatus) {
			
			alert("등록되었습니다.");
			location.reload(); 
		}
	});
	
}

function getOrder(no) {
    var param = "&no="+no;
    
    $.ajax({
        type: "POST",
        url: "order.windy?mode=get_order", 
		dataType: 'json', 
        data: param,
        // dataType: "json",  // 일단 제거
        success: function(ret) {
            console.log('===실제 응답 내용===');
            console.log(ret);
            
            console.log('result : '+ret.result);
            console.log('name : '+ret.name);
            console.log('date : '+ret.date);
            console.log('amount : '+ret.amount);
            console.log('note : '+ret.note);
            
            document.getElementById('modify_name').innerHTML = ret.name;
            document.getElementById('modify_order_date').value = ret.date;
            document.getElementById('modify_order_amount').value = ret.amount;
            document.getElementById('modify_order_note').value = ret.note;
            document.getElementById('modify_no').value = no;

        },
        error: function(xhr, status, error) {
            console.log('===에러 발생===');
            console.log('responseText:', xhr.responseText);
            console.log('status:', status);
        }
    });
}

function ajaxFailed(xmlRequest)	{
	alert(xmlRequest.status+"\n\r"+xmlRequest.statusText+"\n\r"+xmlRequest.responseText);
}
</script>
<title>Insert title here</title>
</head>
<body>
    <div>
        <jsp:include page="../top_menu.jsp"></jsp:include>
        <div class="wrapper_main_contents" >
			<div style="height: 100%;">
				<jsp:include page="../side_menu.jsp"/>
			</div>
			<div class="wrapper_board_contents">
				<div class="wrapper_board">
					<span class="span_board_title">발주 관리</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
							<input type="text" id="search_name" style="width: 120px; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; padding-left: 5px; padding-right: 5px; " value="<%=searchName %>">
							<div style="width: 30px; height: 30px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;" onclick="javascript: goSearch()">
								<img alt="" src="images/img_search.png" width="30px" height="30px">							
							</div>
						</div>
						<div style="width: 50%">
							<button class="btn_basic_150" onclick="javascript:popOpen('.modal-bg1', '.modal_700_700-wrap1', -1);">발주 등록</button>
						</div>
					</div>
					<div style="margin-top: 10px;">
						<table class="tb_basic tb_management">
							<colgroup>
								<col width="10%">
								<col width="20%">
								<col width="15%">
								<col width="15%">
								<col width="15%">
								<col width="25%">
							</colgroup>
							<thead>
								<tr>
									<th>No</th>
									<th>약품/용품명</th>
									<th>발주일</th>
									<th>발주량</th>
									<th>입고량</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<%for(int i=0; i<listOrder.size(); i++) {
									OrderModel model = listOrder.get(i);
									String name = "";
									
									if(model.getSuppliesNo() == -1) {
										name = model.getMedicineName();
									} else if(model.getMedicineNo() == -1) {
										name = model.getSuppliesName();
									}
								%>
								<tr>
									<td onClick="javascript: popOpen('.modal-bg2', '.modal_700_700-wrap2', <%=model.getNo() %>);">
										<%=i+1 %>
										<input type="hidden" id="order_no_<%=i %>" value="<%=model.getNo() %>">
										<input type="hidden" id="supplies_no_<%=i %>" value="<%=model.getSuppliesNo() %>">
										<input type="hidden" id="medicine_no_<%=i %>" value="<%=model.getMedicineNo() %>">
										<input type="hidden" id="name_<%=i %>" value="<%=name %>">
									</td>
									<td onClick="javascript: popOpen('.modal-bg2', '.modal_700_700-wrap2', <%=model.getNo() %>);"><%=name %></td>
									<td onClick="javascript: popOpen('.modal-bg2', '.modal_700_700-wrap2', <%=model.getNo() %>);"><%=model.getDate() %></td>
									<td onClick="javascript: popOpen('.modal-bg2', '.modal_700_700-wrap2', <%=model.getNo() %>);"><%=model.getAmount() %></td>
									<td onClick="javascript: popOpen('.modal-bg2', '.modal_700_700-wrap2', <%=model.getNo() %>);"><%=model.getInAmount() %></td>
									<td>
                    					<button class="btn_basic_100" onclick="javascript: popOpen('.modal-bg3', '.modal_700_700-wrap3', <%=i %>);">입고 등록</button>
                                        <button type="button" class="btn_cancel_100" onclick="javascript: goDelete(<%=model.getNo() %>)">삭제</button>
                                    </td>
								</tr>
								<%} %>
							</tbody>
						</table>
						<!-- 
						<div style="margin-top: 30px;">
							페이징처리
						</div> -->
					</div>
				</div>
			</div>
		</div>
    </div>
	
	<div class="modal-bg1" onClick="javascript:popClose('.modal-bg1', '.modal_700_700-wrap1');"></div>
	<div class="modal_700_700-wrap1">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">발주 등록</span>
				</div>
			</div>
            
			<div class="wrapper_popup" style="margin-top: 10px;">
				<table class="popup_tb_border popup_tb_th100">
					<colgroup>
						<col width="50%">
						<col width="50%">
					</colgroup>
					<tr>
						<td colspan="2">
							<input type="radio" name="add_type" id="in" onchange="javascript: changeList()" value="medicine" >
							<label for="in">약품</label>
							<input type="radio" name="add_type" id="out" onchange="javascript: changeList()" value="supplies">
							<label for="out">용품</label>
						</td>
					</tr>
                    <tr>
						<th>약품/용품명</th>
						<td>
							<select class="popup_select" id="add_medicine_no">
								<%for(int i=0; i<listMedicine.size(); i++) {%>
								<option value="<%=listMedicine.get(i).getNo() %>"><%=listMedicine.get(i).getName() %></option>
								<%} %>
							</select>
							<select class="popup_select" id="add_supplies_no" style="display: none">
								<%for(int i=0; i<listSupplies.size(); i++) {%>
								<option value="<%=listSupplies.get(i).getNo() %>"><%=listSupplies.get(i).getName() %></option>
								<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<th>발주일</th>
						<td><input type="text" class="input_text" id="add_order_date" value="<%=sdf.format(day) %>"></td>
					</tr>
					<tr>
						<th>발주량</th>
						<td><input type="text" class="input_text" id="add_order_amount"></td>
					</tr>
					<tr>
						<th>비고</th>
						<td><input type="text" class="input_text" id="add_order_note"></td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150" onclick="javascript: addOrder()">등록</button>
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg1', '.modal_700_700-wrap1');">취소</button>
                </div>
            </div>
		</div>
    </div>
    
	<div class="modal-bg2" onClick="javascript:popClose('.modal-bg2', '.modal_700_700-wrap2');"></div>
	<div class="modal_700_700-wrap2">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">발주 수정</span>
				</div>
			</div>
            
			<div class="wrapper_popup" style="margin-top: 10px;">
				<table class="popup_tb_border popup_tb_th100">
					<colgroup>
						<col width="50%">
						<col width="50%">
					</colgroup>
                    <tr>
						<th>약품/용품명</th>
						<td>
							<input type="hidden" id="modify_no">
							<label for="in" id="modify_name"></label>
						</td>
					</tr>
					<tr>
						<th>발주일</th>
						<td><input type="text" class="input_text" id="modify_order_date" ></td>
					</tr>
					<tr>
						<th>발주량</th>
						<td><input type="text" class="input_text" id="modify_order_amount"></td>
					</tr>
					<tr>
						<th>비고</th>
						<td><input type="text" class="input_text" id="modify_order_note"></td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150" onclick="javascript: goModify()">수정</button>
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg2', '.modal_700_700-wrap2');">취소</button>
                </div>
            </div>
		</div>
    </div>
    
	<div class="modal-bg3" onClick="javascript:popClose('.modal-bg3', '.modal_700_700-wrap3');"></div>
	<div class="modal_700_700-wrap3">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">입고 등록</span>
				</div>
			</div>
            
			<div class="wrapper_popup" style="margin-top: 10px;">
				<table class="popup_tb_border popup_tb_th100">
					<colgroup>
						<col width="50%">
						<col width="50%">
					</colgroup>
                    <tr>
						<th>약품/용품명</th>
						<td>
							<input type="hidden" id="supplies_no">
							<input type="hidden" id="medicine_no">
							<input type="hidden" id="in_out_order_no">
							<label for="in" id="in_out_name"></label>
						</td>
					</tr>
					<tr>
						<th>입고일</th>
						<td><input type="text" class="input_text" id="in_out_date" value="<%=sdf.format(day) %>"></td>
					</tr>
					<tr>
						<th>입고량</th>
						<td><input type="text" class="input_text" id="in_out_amount"></td>
					</tr>
					<tr>
						<th>비고</th>
						<td><input type="text" class="input_text" id="in_out_note"></td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150" onclick="javascript: addInOut()">등록</button>
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg3', '.modal_700_700-wrap3');">취소</button>
                </div>
            </div>
		</div>
    </div>
</body>
</html>