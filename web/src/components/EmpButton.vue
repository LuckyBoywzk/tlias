<template>
  <div>
    <!-- 新增删除按钮 -->
    <el-row style="text-align: left">
      <el-button type="danger" @click="del">批量删除</el-button>
      <el-button type="primary" @click="add">+新增员工</el-button>
    </el-row>
    <br>
    <!-- 新增员工对话框 -->
    <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="头像" :label-width="formLabelWidth">
          <el-input v-model="form.image" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-input v-model="form.gender" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="职位" :label-width="formLabelWidth">
          <el-input v-model="form.job" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="insert">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Bus from "@/utils/EventBus"
import axios from 'axios';
export default {
  data() {
    return {
      dialogFormVisible: false,
      form: {
        name: '',
        image: '',
        gender: '',
        job: ''
      },
      formLabelWidth: '120px'
    };
  },
  methods: {
    insert () {
      this.dialogFormVisible = false
      // console.log(this.form)
      this.$emit('send', this.form)
    },
    add() {
      this.dialogFormVisible = true
    },
    // 批量删除
    async del() {
      let arr = new Array()
      // console.log(this.selection)
      for(let i = 0; i < this.selection.length; i++) {
        arr[i] = this.selection[i].id
      }
      await axios.delete('http://localhost:8080/Emps/' + arr).then((res) => {
        if (res.data.msg === 'success') {
          this.$emit('select')
          // 消息提示
          this.$message({
          message: '恭喜你，删除成功',
          type: 'success'
        })
        }
      })
    }
  },
  mounted() {
    Bus.$on("edit", (flag) => {
      this.dialogFormVisible = flag
    })
  },
  props: {
    selection: {
      type: Array,
      // required: true,
      // default: [],
      // validator(value) {
      //   return true
      // }
    }
  }
};
</script>

<style>

</style>