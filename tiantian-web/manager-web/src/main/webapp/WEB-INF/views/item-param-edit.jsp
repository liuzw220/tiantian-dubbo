<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="itemeParamEditForm" class="itemForm" method="post">
	<table cellpadding="5" style="margin-left: 30px" id="itemParamAddTable"
		class="itemParam">
		<tr>
			<td>商品类目:</td>
			<td>
			<span id="itemCatName"></span>
				<input type="hidden" name="id">
				<input type="hidden" name="itemCatId"></input></td>
		</tr>
		<tr class="hide addGroupTr">
			<td>规格参数:</td>
			<td>
				<ul>
					<li><a href="javascript:void(0)"
						class="easyui-linkbutton addGroup">添加分组</a></li>
				</ul>
			</td>
		</tr>
		<tr>
			<td>
				<div id="itemParamData" class="itemParamAddTemplate" style="display: none;">
				
				</div>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><a href="javascript:void(0)"
				class="easyui-linkbutton submit">提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton close">关闭</a></td>
		</tr>
	</table>
	
</form>
<script style="">
	$(function() {
		TAOTAO.initItemCat({
			fun : function(node) {
				$(".addGroupTr").hide().find(".param").remove();
				//  判断选择的目录是否已经添加过规格
				$.ajax({
					type : 'get',
					url : "/rest/item/param/" + node.id,
					success : function(data) {
						if (data) {
							$.messager.alert("提示", "该类目已经添加，请选择其他类目。",
									undefined, function() {
										$("#itemParamAddTable .selectItemCat")
												.click();
									});
						} else
							$(".addGroupTr").show();
					}
				})
			}
		});

		$(".addGroup").click(function() {
			var temple = $(".itemParamAddTemplate li").eq(0).clone();
			$(this).parent().parent().append(temple);
			temple.find(".addParam").click(function() {
				var li = $(".itemParamAddTemplate li").eq(2).clone();
				li.find(".delParam").click(function() {
					$(this).parent().remove();
				});
				li.appendTo($(this).parentsUntil("ul").parent());
			});
			temple.find(".delParam").click(function() {
				$(this).parent().remove();
			});
		});

		$("#itemParamAddTable .close").click(function() {
			$(".panel-tool-close").click();
		});

		$("#itemParamAddTable .submit").click(
				function() {
					var params = [];
					var groups = $("#itemParamAddTable [name=group]");
					groups.each(function(i, e) {
						var p = $(e).parentsUntil("ul").parent().find(
								"[name=param]");
						var _ps = [];
						p.each(function(_i, _e) {
							var _val = $(_e).siblings("input").val();
							if ($.trim(_val).length > 0) {
								_ps.push(_val);
							}
						});
						var _val = $(e).siblings("input").val();
						if ($.trim(_val).length > 0 && _ps.length > 0) {
							params.push({
								"group" : _val,
								"params" : _ps
							});
						}
					});
					$.ajax({
						type : 'post',
						url : '/rest/item/param',
						data : 'itemCatId='
								+ $("#itemParamAddTable [name=cid]").val()
								+ '&paramData=' + JSON.stringify(params),
						success : function() {
							$.messager.alert('提示', '新增商品规格成功!', undefined,
									function() {
										$(".panel-tool-close").click();
										$("#itemParamList").datagrid("reload");
									});
						}
					})
					/* var url = "/rest/item/param/save/"+$("#itemParamAddTable [name=cid]").val();
					$.post(url,{"paramData":JSON.stringify(params)},function(data){
						if(data.status == 200){
							$.messager.alert('提示','新增商品规格成功!',undefined,function(){
								$(".panel-tool-close").click();
								$("#itemParamList").datagrid("reload");
							});
						}
					}); */
				});
	});
</script>