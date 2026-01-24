<template>
  <div ref='datetimerange'>
    <el-date-picker v-model="datetime" :disabled="disabled" :clearable="clearable" :type="type" :size="size"
                    :start-placeholder="startPlaceholder" :end-placeholder="endPlaceholder" :format="format"
                    :unlink-panels="unlinkPanels" :range-separator="rangeSeparator"
                    :value-format="valueFormat" :picker-options="pickerOptions" :default-time="defaultTime"
                    align="center" @change="change">
    </el-date-picker>
  </div>
</template>
<script>
export default {
  name: 'dateTimeRange',
  props: {
    unlinkPanels: {
      type: Boolean,
      default: true
    },
    clearable: {
      type: Boolean,
      default: true
    },
    shortcutsType: {
      type: String,
      default: 'nearly'
    },
    startTime: {
      type: String,
      default: ''
    },
    type: {
      type: String,
      default: 'daterange'
    },
    endTime: {
      type: String,
      default: ''
    },
    valueFormat: {
      type: String,
      default: 'yyyy-MM-dd HH:mm:ss'
    },
    format: {
      type: String,
      default: undefined
    },
    size: {
      type: String,
      default: ''
    },
    disabled: {
      type: Boolean,
      default: false
    },
    defaultTime: {
      type: Array,
      default() {
        return ['00:00:00','00:00:00'];
      }
    },
    rangeSeparator: {
      type: String,
      default: '-'
    },
    startPlaceholder: {
      type: String,
      default: '开始时间'
    },
    endPlaceholder: {
      type: String,
      default: '结束时间'
    }
  },
  data() {
    return {
      datetime: [],
      defaultStartTime: '',
      defaultEndTime: '',
      pickerOptions: {
        shortcuts: [
          {
            text: "近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "近一月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "近一季度",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "近半年",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 180);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "近一年",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 365);
              picker.$emit("pick", [start, end]);
            }
          }
        ],
      },
    }
  },
  computed: {
  },
  watch: {
    startTime(newVal, oldVal) {
      if (newVal) {
        this.datetime = [newVal, this.endTime]
      } else {
        this.datetime = ''
      }
    },
    endTime(newVal, oldVal) {
      if (newVal) {
        this.datetime = [this.startTime, newVal]
      } else {
        this.datetime = ''
      }
    },
    datetime(newVal, oldVal) {
      if (newVal) {
        this.updateStartTime(newVal[0])
        this.updateEndTime(newVal[1])
      } else {
        this.updateStartTime('')
        this.updateEndTime('')
      }
    }
  },
  mounted() {
    if (this.startTime && this.endTime) {
      this.datetime = [this.startTime, this.endTime]
    }
  },
  methods: {
    change(e) {
      if (e) {
        this.defaultStartTime = e[0]
        this.defaultEndTime = e[1]
        this.$emit('change', {
          startTime: e[0],
          endTime: e[1]
        })
      } else {
        this.defaultStartTime = ""
        this.defaultEndTime = ""
        this.$emit('change', {
          startTime: "",
          endTime: ""
        })
      }

    },
    updateStartTime(time) {
      this.$emit('update:startTime', time)
      return time
    },
    updateEndTime(time) {
      this.$emit('update:endTime', time)
      return time
    }
  }
}
</script>
<style lang="scss">
@import "src/assets/css/formFont.scss";
</style>
<style scoped lang="scss">
/deep/ .el-range-editor.is-disabled input{
  color: black !important;
}
/deep/ .el-range-editor.is-disabled .el-range-separator {
  color: black !important;
}
</style>