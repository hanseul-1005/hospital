<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="windy.hospital.model.SuppliesModel"%>
<%@page import="windy.hospital.model.InOutModel"%>
<%@page import="windy.hospital.model.MedicineModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

Calendar calendar = Calendar.getInstance();
Date day = calendar.getTime();

SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd", Locale.KOREAN);

String searchName = (String) request.getAttribute("name");
String type = (String) request.getAttribute("type");

ArrayList<InOutModel> listInOut = (ArrayList<InOutModel>) request.getAttribute("listInOut");

ArrayList<MedicineModel> listMedicine = (ArrayList<MedicineModel>) request.getAttribute("listMedicine");
ArrayList<SuppliesModel> listSupplies = (ArrayList<SuppliesModel>) request.getAttribute("listSupplies");
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
	
	if(num=='.modal-bg3') {
		var inoutNo = document.getElementById("no_"+no).value;
		var name = document.getElementById("name_"+no).value;
		var amount = document.getElementById("amount_"+no).value;
		var date = document.getElementById("date_"+no).value;
		var note = document.getElementById("note_"+no).value;
		var classify = document.getElementById('classify_'+no).value;
		
		var medicineNo = document.getElementById('medicine_no_'+no).value;
		var suppliesNo = document.getElementById('supplies_no_'+no).value;
		
		document.getElementById('modify_in_no').value = inoutNo;
		document.getElementById('modify_in_name').innerHTML = name;
		document.getElementById('modify_in_ex_amount').value = amount;
		document.getElementById('modify_in_date').value = date;
		document.getElementById('modify_in_amount').value = amount;
		document.getElementById('modify_in_note').value = note;
		document.getElementById('modify_in_classify').value = classify;
		
		document.getElementById('modify_in_medicine_no').value = medicineNo;
		document.getElementById('modify_in_supplies_no').value = suppliesNo;

		modalBg.show();
		modalPop.show();
	}
	else if(num=='.modal-bg4') {
		var inoutNo = document.getElementById("no_"+no).value;
		var name = document.getElementById("name_"+no).value;
		var amount = document.getElementById("amount_"+no).value;
		var date = document.getElementById("date_"+no).value;
		var note = document.getElementById("note_"+no).value;
		var classify = document.getElementById('classify_'+no).value;
		
		var medicineNo = document.getElementById('medicine_no_'+no).value;
		var suppliesNo = document.getElementById('supplies_no_'+no).value;
		
		document.getElementById('modify_out_no').value = inoutNo;
		document.getElementById('modify_out_name').innerHTML = name;
		document.getElementById('modify_out_ex_amount').value = amount;
		document.getElementById('modify_out_date').value = date;
		document.getElementById('modify_out_amount').value = "-"+amount;
		document.getElementById('modify_out_note').value = note;
		document.getElementById('modify_out_classify').value = classify;
		
		document.getElementById('modify_out_medicine_no').value = medicineNo;
		document.getElementById('modify_out_supplies_no').value = suppliesNo;

		modalBg.show();
		modalPop.show();
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


function changeList(type) {

	if(type=='add_out') {
		var option = $("input[name='add_out_type']:checked").val();
		
		if(option=='medicine') {
			document.getElementById('add_out_medicine_no').style.display = "";
			document.getElementById('add_out_supplies_no').style.display = "none";
		} else {
			document.getElementById('add_out_medicine_no').style.display = "none";
			document.getElementById('add_out_supplies_no').style.display = "";
		}
	}
	else if(type=='add_in') {
	var option = $("input[name='add_in_type']:checked").val();
		
		if(option=='medicine') {
			document.getElementById('add_in_medicine_no').style.display = "";
			document.getElementById('add_in_supplies_no').style.display = "none";
		} else {
			document.getElementById('add_in_medicine_no').style.display = "none";
			document.getElementById('add_in_supplies_no').style.display = "";
		}
	}
}


function addOut() {

	var option = $("input[name='add_out_type']:checked").val();
	
	var suppliesNo = document.getElementById('add_out_supplies_no').value;
	var medicineNo = document.getElementById('add_out_medicine_no').value;
	
	if(option=='medicine') {
		suppliesNo = -1;
	} else {
		medicineNo = -1;
	}
	var date = document.getElementById('add_out_date').value;
	var amount = document.getElementById('add_out_amount').value;
	var note = document.getElementById('add_out_note').value;
	
	var param = "&supplies_no="+suppliesNo+"&medicine_no="+medicineNo+"&date="+date+"&amount="+amount+"&note="+note;
		
	$.ajax({
		type: "post", 
		url: "in_out.windy?mode=add_out", 
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


function udpateOut() {
	
	var no = document.getElementById('modify_out_no').value;
	var date = document.getElementById('modify_out_date').value;
	var exAmount = document.getElementById('modify_out_ex_amount').value;
	var amount = document.getElementById('modify_out_amount').value;
	var classify = document.getElementById('modify_out_classify').value;
	
	console.log("amount : "+amount);
	var note = document.getElementById('modify_out_note').value;
	
	var medicineNo = document.getElementById('modify_out_medicine_no').value;
	var suppliesNo = document.getElementById('modify_out_supplies_no').value;
	
	var param = "&no="+no+"&date="+date+"&ex_amount="+exAmount+"&amount="+amount+"&note="+note+"&medicine_no="+medicineNo+"&supplies_no="+suppliesNo+"&classify="+classify;
		
	$.ajax({
		type: "post", 
		url: "in_out.windy?mode=update_out", 
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


function addIn() {

	var option = $("input[name='add_in_type']:checked").val();
	
	var suppliesNo = document.getElementById('add_in_supplies_no').value;
	var medicineNo = document.getElementById('add_in_medicine_no').value;
	
	if(option=='medicine') {
		suppliesNo = -1;
	} else {
		medicineNo = -1;
	}
	var date = document.getElementById('add_in_date').value;
	var amount = document.getElementById('add_in_amount').value;
	var note = document.getElementById('add_in_note').value;
	
	var param = "&supplies_no="+suppliesNo+"&medicine_no="+medicineNo+"&date="+date+"&amount="+amount+"&note="+note;
		
	$.ajax({
		type: "post", 
		url: "in_out.windy?mode=add_in", 
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

function udpateIn() {
	
	var no = document.getElementById('modify_in_no').value;
	var date = document.getElementById('modify_in_date').value;
	var exAmount = document.getElementById('modify_in_ex_amount').value;
	var amount = document.getElementById('modify_in_amount').value;
	var classify = document.getElementById('modify_in_classify').value;
	
	console.log("amount : "+amount);
	var note = document.getElementById('modify_in_note').value;
	
	var medicineNo = document.getElementById('modify_in_medicine_no').value;
	var suppliesNo = document.getElementById('modify_in_supplies_no').value;
	
	var param = "&no="+no+"&date="+date+"&ex_amount="+exAmount+"&amount="+amount+"&note="+note+"&medicine_no="+medicineNo+"&supplies_no="+suppliesNo+"&classify="+classify;
		
	$.ajax({
		type: "post", 
		url: "in_out.windy?mode=update_in", 
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








function goAdd() {
	location.href="medicine.windy?menu=add";
}

function goSearch() {
	var name = document.getElementById('search_name').value;
	var type = document.getElementById('type').value;
	
	location.href="in_out.windy?menu=list&name="+name+"&type="+type;
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

function goModify() {

	var no = document.getElementById('no').value;
	var name = document.getElementById('name').value;
	var date = document.getElementById('date').value;
	var amount = document.getElementById('amount').value;
	var note = document.getElementById('note').value;
	
	var param = "&no="+no+"&name="+name+"&date="+date+"&amount="+amount+"&note="+note;
		
	$.ajax({
		type: "post", 
		url: "medicine.windy?mode=update", 
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
					<span class="span_board_title">약품 입/출고 관리</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
							<select class="select_bx" style="width: 16%; margin-right: 10px;" id="type">
								<option value="m" <%if("m".equals(type)) {%> selected="selected" <%} %>>약품</option>
								<option value="s" <%if("s".equals(type)) {%> selected="selected" <%} %>>비품</option>
							</select>
							<input type="text" id="search_name" style="width: 120px; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; padding-left: 5px; padding-right: 5px; " value="<%=searchName %>">
							<div style="width: 30px; height: 30px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;" onclick="javascript: goSearch()">
								<img alt="" src="images/img_search.png" width="30px" height="30px">							
							</div>
						</div>
						<div style="width: 50%">
							<button class="btn_basic_150" onClick="javascript:popOpen('.modal-bg1', '.modal_700_700-wrap1', -1);">입고 등록</button>
							<button class="btn_basic_150" onClick="javascript:popOpen('.modal-bg2', '.modal_700_700-wrap2', -1);">출고 등록</button>
						</div>
					</div>
					<div style="margin-top: 10px;">
						<table class="tb_basic tb_management">
							<colgroup>
								<col width="10%">
								<col width="15%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<!-- <col width="10%"> -->
							</colgroup>
							<thead>
								<tr>
									<th>No</th>
									<th>구분</th>
									<th>약품/용품명</th>
									<th>입고/출고일</th>
									<th>입출고량</th>
									<!-- <th>삭제</th> -->
								</tr>
							</thead>
							<tbody>
								<%for(int i=0; i<listInOut.size(); i++) {
									InOutModel model = listInOut.get(i);

									String name = model.getSuppliesName();
									if(0 < model.getSuppliesNo()) {
										name = model.getSuppliesName();
									} else if(0 < model.getMedicineNo()) {
										name = model.getMedicineName();
									}
									int num = 3;
									if("출고".equals(model.getClassify())) {
										num = 4;
									} else {
										num = 3;
									}
								%>
								<tr>
									<td onClick="javascript: popOpen('.modal-bg<%=num %>', '.modal_700_700-wrap<%=num %>', <%=i %>);">
										<%=i+1 %>
										<input type="hidden" id="no_<%=i %>" value="<%=model.getNo() %>"> 
										<input type="hidden" id="medicine_no_<%=i %>" value="<%=model.getMedicineNo() %>">
										<input type="hidden" id="supplies_no_<%=i %>" value="<%=model.getSuppliesNo() %>"> 
										<input type="hidden" id="no_<%=i %>" value="<%=model.getNo() %>"> 
										<input type="hidden" id="name_<%=i %>" value="<%=name %>"> 
										<input type="hidden" id="date_<%=i %>" value="<%=model.getDate() %>"> 
										<input type="hidden" id="amount_<%=i %>" value="<%=String.valueOf(model.getAmount()).replaceAll("-", "") %>"> 
										<input type="hidden" id="note_<%=i %>" value="<%=model.getNote() %>">
										<input type="hidden" id="classify_<%=i %>" value="<%=model.getClassify() %>">
									</td>
									<td onClick="javascript: popOpen('.modal-bg<%=num %>', '.modal_700_700-wrap<%=num %>', <%=i %>);"><%=model.getClassify() %></td>
									<td onClick="javascript: popOpen('.modal-bg<%=num %>', '.modal_700_700-wrap<%=num %>', <%=i %>);"><%=name %></td>
									<td onClick="javascript: popOpen('.modal-bg<%=num %>', '.modal_700_700-wrap<%=num %>', <%=i %>);"><%=model.getDate() %></td>
									<td onClick="javascript: popOpen('.modal-bg<%=num %>', '.modal_700_700-wrap<%=num %>', <%=i %>);"><%=model.getAmount() %></td>
									<%-- <td>
                                        <button type="button" class="btn_cancel_100" onclick="javascript: goDelete(<%=model.getNo() %>)">비활성화</button>
                                    </td> --%>
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
						<td colspan="2">
							<input type="radio" name="add_in_type" id="in" onchange="javascript: changeList('add_out')" value="medicine" checked="checked">
							<label for="in">약품</label>
							<input type="radio" name="add_in_type" id="out" onchange="javascript: changeList('add_out')" value="supplies">
							<label for="out">용품</label>
						</td>
					</tr>
                    <tr>
						<th>약품/용품명</th>
						<td>
							<select class="popup_select" id="add_in_medicine_no">
								<%for(int i=0; i<listMedicine.size(); i++) {%>
								<option value="<%=listMedicine.get(i).getNo() %>"><%=listMedicine.get(i).getName() %></option>
								<%} %>
							</select>
							<select class="popup_select" id="add_in_supplies_no" style="display: none">
								<%for(int i=0; i<listSupplies.size(); i++) {%>
								<option value="<%=listSupplies.get(i).getNo() %>"><%=listSupplies.get(i).getName() %></option>
								<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<th>입고일</th>
						<td><input type="text" class="input_text" id="add_in_date" value="<%=sdf.format(day) %>"></td>
					</tr>
					<tr>
						<th>입고량</th>
						<td><input type="text" class="input_text" id="add_in_amount"></td>
					</tr>
					<tr>
						<th>비고</th>
						<td><input type="text" class="input_text" id="add_in_note"></td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150" onclick="javascript: addIn()">등록</button>
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
					<span class="span_popup_title">출고 등록</span>
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
							<input type="radio" name="add_out_type" id="in" onchange="javascript: changeList('add_out')" value="medicine" checked="checked">
							<label for="in">약품</label>
							<input type="radio" name="add_out_type" id="out" onchange="javascript: changeList('add_out')" value="supplies">
							<label for="out">용품</label>
						</td>
					</tr>
                    <tr>
						<th>약품/용품명</th>
						<td>
							<select class="popup_select" id="add_out_medicine_no">
								<%for(int i=0; i<listMedicine.size(); i++) {%>
								<option value="<%=listMedicine.get(i).getNo() %>"><%=listMedicine.get(i).getName() %></option>
								<%} %>
							</select>
							<select class="popup_select" id="add_out_supplies_no" style="display: none">
								<%for(int i=0; i<listSupplies.size(); i++) {%>
								<option value="<%=listSupplies.get(i).getNo() %>"><%=listSupplies.get(i).getName() %></option>
								<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<th>출고일</th>
						<td><input type="text" class="input_text" id="add_out_date" value="<%=sdf.format(day) %>"></td>
					</tr>
					<tr>
						<th>출고량</th>
						<td><input type="text" class="input_text" id="add_out_amount" value="-"></td>
					</tr>
					<tr>
						<th>비고</th>
						<td><input type="text" class="input_text" id="add_out_note"></td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150" onclick="javascript: addOut()">등록</button>
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
					<span class="span_popup_title">입고 수정</span>
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
							<input type="hidden" id="modify_in_no"/>
							<input type="hidden" id="modify_in_medicine_no"/>
							<input type="hidden" id="modify_in_supplies_no"/>
							<input type="hidden" id="modify_in_ex_amount"/>
							<input type="hidden" id="modify_in_classify"/>
							<label for="in" id="modify_in_name"></label>
						</td>
					</tr>
					<tr>
						<th>입고일</th>
						<td><input type="text" class="input_text" id="modify_in_date"></td>
					</tr>
					<tr>
						<th>입고량</th>
						<td><input type="text" class="input_text" id="modify_in_amount"></td>
					</tr>
					<tr>
						<th>비고</th>
						<td><input type="text" class="input_text" id="modify_in_note"></td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150" onclick="javascript: udpateIn()">등록</button>
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg3', '.modal_700_700-wrap3');">취소</button>
                </div>
            </div>
		</div>
    </div>
    
	<div class="modal-bg4" onClick="javascript:popClose('.modal-bg4', '.modal_700_700-wrap4');"></div>
	<div class="modal_700_700-wrap4">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">출고 수정</span>
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
							<input type="hidden" id="modify_out_no"/>
							<input type="hidden" id="modify_out_medicine_no"/>
							<input type="hidden" id="modify_out_supplies_no"/>
							<input type="hidden" id="modify_out_ex_amount"/>
							<input type="hidden" id="modify_out_classify"/>
							<label for="in" id="modify_out_name"></label>
						</td>
					</tr>
					<tr>
						<th>출고일</th>
						<td><input type="text" class="input_text" id="modify_out_date" ></td>
					</tr>
					<tr>
						<th>출고량</th>
						<td><input type="text" class="input_text" id="modify_out_amount"></td>
					</tr>
					<tr>
						<th>비고</th>
						<td><input type="text" class="input_text" id="modify_out_note"></td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150" onclick="javascript: udpateOut()">등록</button>
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg4', '.modal_700_700-wrap4');">취소</button>
                </div>
            </div>
		</div>
    </div>
</body>
</html>