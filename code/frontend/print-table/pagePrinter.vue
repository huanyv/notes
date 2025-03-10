<template>
  <el-dialog :title="title" :visible.sync="visible" width="90%" :before-close="handleClose" center append-to-body>
    <div class="w-100 h-100">
      <div class="w-100" ref="print">
        <div class="header-view w-100">
          <div class="title-view w-100 single-line" v-if="titleInputMode">
            <el-input v-model="printMap.title" placeholder="请输入标头" style="width: 800px;" id="titleInput"></el-input>
          </div>
          <div class="title-view w-100 single-line" v-else>{{ printMap.title }}</div>
        </div>
        <div class="w-100 body-view">
          <!--  table -->
          <table class="dataTable" cellspacing="0">
            <thead>
            <tr>
              <th :style="'width: ' + item.width + '%;'" v-for="item in printMap.header" :key="item.code">{{item.name}}</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in printMap.data" :key="item.id">
              <td v-for="obj in printMap.header" :key="obj.code" :align="obj.align">{{item[obj.code]}}</td>
            </tr>
            </tbody>
          </table>

        </div>
      </div>
    </div>
    <div slot="footer" class="w-100 flex-row no-print" style="height: 44px;">
      <el-button class="el-icon-printer" type="primary" @click="handlePrint">打印</el-button>
    </div>
  </el-dialog>
</template>
<script>
import Printer from "./print";
export default {
  name: 'pagePrinter',
  components: {},
  data() {
    return {
      visible: false,
      inMobile: false,
      titleInputMode: true,
      printData: {
        'title': '',
        'titleClass': null,
        'subTitle': '',
        'subTitleClass': null,
        'data': []
      },
      rowSpan: 12
    }
  },
  props: {
    title: {
      type: String,
      default: () => null
    },
    //是否随机项目
    printMap: {
      type: Object,
      default: () => {
        return {
          'title': '',
          'titleClass': null,
          'subTitle': '',
          'subTitleClass': null,
          'data': []
        }
      }
    },
  },
  watch: {
    printMap: {
      deep: true,
      handler(val) {
        if (val) {
          this.updatePrintData()
        }
      }
    },
  },
  created() {

  },
  filters: {
    textFilter(val) {
      let v = val.replace(/：/g, '');
      return v
    }
  },
  methods: {
    handleClose() {
      this.visible = false;
      this.$emit('update:visible', false)
      this.$emit('close', {})
      this.titleInputMode = true;
    },
    updatePrintData() {
      let m = {
        'title': this.printMap.title,
        'titleClass': this.printMap.titleClass,
        'subTitle': this.printMap.subTitle,
        'subTitleClass': this.printMap.subTitleClass,
        'data': this.printMap.data
      }

      this.visible = true
      this.$nextTick(() => {

      })
    },
    handlePrint() {
      this.titleInputMode = false;
      this.$nextTick((_) => {
        // this.$print(this.$refs.print);
        Printer(this.$refs.print);
      })
    },
  }
}
</script>

<style lang="scss" scoped>

.header-view {
  width: 100%;
  padding: 10px 0px;

  //border-bottom: 1px solid #ccc;
}

.section-view {
  font-size: 12px;
  text-align: left;
  color: #333;
  margin-left: 10px;
  padding-bottom: 5px;
}

.title-view {
  font-size: 18px;
  text-align: center;
  color: black;
}

.cnt-view {
  height: calc(100% - 44px);
}

.body-view {
  height: calc(100% - 64px);
}

.bd-left {
  border-left: 1px solid #ccc;
}

.bd-right {
  border-right: 1px solid #ccc;
}

.bd-top {
  border-top: 1px solid #ccc;
}

.row-view {
  border-bottom: 1px solid #ccc;
}

.flex-row {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.cell-view {
  //min-height: 38px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}

.main-lb {
  font-size: 10px;
  color: #444;
  width: 40%;
  font-weight: 500;
  padding-left: 5px;
  //border-right: 1px solid #ccc;
}

.value-lb {
  font-size: 11px;
  color: black;
  width: 60%;
  border-left: 1px solid #ccc;
  white-space: pre-line;
  word-break: break-all;
  padding: 4px 10px;
}

.value-pre {
  white-space: pre;
}

::v-deep {
  .el-dialog {
    width: 100%;
    //height: 100%;
  }

  .el-dialog__body {
    height: 100%;
    padding: 10px 20px;
  }

  .el-dialog--center {
    margin-top: 30px !important;
    margin-bottom: 30px !important;
  }

  .el-divider--horizontal {
    margin: 0;
  }

  .is-horizontal {
    height: 0px;
    display: none;
  }

  .el-scrollbar__wrap {
    overflow-x: hidden;
  }
}

$table-border-color: #ccc;
$table-border-size: 1px;
.dataTable {
  margin: auto;
  //width: 90%;
  border: $table-border-color solid $table-border-size;
  color: black;
  thead {
    tr {
      border: $table-border-color solid $table-border-size;
      height: 50px;
      th {
        border: $table-border-color solid $table-border-size;
      }
    }
  }
  tbody {
    tr {
      border: $table-border-color solid $table-border-size;
      height: 30px;
      td {
        border: $table-border-color solid $table-border-size;
        padding-left: 10px;
      }
    }
  }
}

/deep/ #titleInput .el-input__inner {
  text-align: center;
}
/deep/ #titleInput {
  text-align: center;
}

//隐藏页眉页脚
@media print {
  body {
    margin: 0;
    padding: 0;
  }

  @page {
    margin: 20px 20px 20px 20px;
  }

  header,
  footer {
    display: none;
  }

  table { page-break-after:auto }
  tr    { page-break-inside:avoid; page-break-after:auto }
  td    { page-break-inside:avoid; page-break-after:auto }
  thead { display:table-header-group }//表格的行头
  tfoot { display:table-footer-group } //表格的行尾
}
</style>