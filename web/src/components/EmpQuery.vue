<template>
  <div>
    <!-- 员工查询对话框 -->
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="formInline.name" placeholder="姓名"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="formInline.gender" placeholder="性别">
          <el-option label="男" value="1"></el-option>
          <el-option label="女" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="入职时间">
        <el-col :span="11">
          <el-form-item prop="date1">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="formInline.begin"
              style="width: 100%"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="1">-</el-col>
        <el-col :span="11">
          <el-form-item prop="date2">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="formInline.end"
              style="width: 100%"
            ></el-date-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" style="">查询</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {
        name: "",
        gender: "",
        begin: "",
        end: "",
      },
    };
  },
  methods: {
    onSubmit() {
      //对开始日期进行格式化
      let year1 = this.formInline.begin.getFullYear()
      let month1 = this.formInline.begin.getMonth() + 1
      let day1 = this.formInline.begin.getDate()
      year1 = year1 < 10 ? "0" + year1 : year1
      month1 = month1 < 10 ? "0" + month1 : month1
      day1 = day1 < 10 ? "0" + day1 : day1
      //对结束日期进行格式化
      let year2 = this.formInline.end.getFullYear()
      let month2 = this.formInline.end.getMonth() + 1
      let day2 = this.formInline.end.getDate()
      year2 = year2 < 10 ? "0" + year2 : year2
      month2 = month2 < 10 ? "0" + month2 : month2
      day2 = day2 < 10 ? "0" + day2 : day2
      // console.log(`${year}-${month}-${day}`)
      const d1 = `${year1}-${month1}-${day1}`
      // console.log(d1)
      const d2 = `${year2}-${month2}-${day2}`
      // console.log(d2)
      this.formInline.begin = d1
      this.formInline.end = d2
      // console.log(this.formInline)
      this.$emit('select', this.formInline)
    },
  },
};
</script>

<style>
.demo-form-inline {
  text-align: left;
}
</style>