<template>
  <div>
    <!--
      <dropdown-tag-select v-model="value" :options="options" label-key="name" value-key="id" @sort="sortItems"></dropdown-tag-select>
    -->
    <el-tag v-for="tag in selectOptions" :key="tag[valueKey]" style="margin-right: 10px" :closable="!disabled"
            @close="closeTag(tag)" :type="tagType" :effect="tagEffect">
      {{ tag[labelKey] }}
    </el-tag>
    <el-dropdown @command="selectDropdown" :trigger="trigger" v-if="!disabled">
      <span class="el-dropdown-link" style="cursor: pointer; color: #1C67EA">
        {{ placeholder }}<i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown" style="max-height: 300px;overflow-y: auto;">
        <el-dropdown-item :command="item[valueKey]" v-for="item in options" :key="item[valueKey]"
                          :disabled="item.disable">
          {{ item[labelKey] }}
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "dropdown-tag-select",
  props: {
    value: {type: Array, default: () => []},
    options: {type: Array, default: () => []},
    disabled: {type: Boolean, default: false},
    labelKey: {type: String, default: ""},
    valueKey: {type: String, default: ""},
    tagType: {type: String, default: ""},
    tagEffect: {type: String, default: "light"},
    trigger: {type: String, default: "hover"},
    placeholder: {type: String, default: "请选择"},
    selected: {type: Array, default: undefined},
  },
  model: {
    prop: 'value',
    event: 'change'
  },
  computed: {
    selectOptions() {
      if (this.selected) {
        return this.selected;
      }
      let arr = [];
      this.options.forEach(item => item.disable = false);
      for (let i = 0; i < this.value.length; i++) {
        let obj = this.options.find(item => {
          if (item[this.valueKey] === this.value[i]) {
            item.disable = true;
            return item;
          }
        });
        if (obj) {
          arr.push(obj);
        }
      }
      this.$emit("sort", arr);
      return arr;
    }
  },
  methods: {
    selectDropdown(val) {
      if (this.value.indexOf(val) < 0) {
        const newVal = [...this.value, val]
        this.$emit('change', newVal)
      }
    },
    closeTag(val) {
      const newVal = this.value.filter(item => item !== val.id)
      this.$emit('change', newVal)
    }
  }
}
</script>

<style scoped>

</style>
