
<template>


	<template v-if="printIsShow">
		<print-view :visible.sync="printViewVisible" :print-map="printData" @close="printViewVisible = false"></print-view>
	</template>

</template>


// 打印按钮点击事件
    printSummary() {
      // 设置表头
      let tableHeader = [{
        code: "col1",
        name: "标头1",
        width: "20",
        align: "left"
      },{
        code: "col2",
        name: "标头1",
        width: "5",
        align: "left"
      }];

      // 组装数据
      let list = this.dataList;

      // 设置标题
      let tableTitle = "标题";

      // 打印数据
      this.printData = {
        title: tableTitle,
        titleClass: "",
        subTitle: "",
        subTitleClass: "",
        data: list,
        header: tableHeader
      }

      this.printViewVisible = true;
    },
