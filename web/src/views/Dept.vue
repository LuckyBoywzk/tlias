<template>
  <div>
    <!-- 分割线 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>部门管理</el-breadcrumb-item>
    </el-breadcrumb>
    <br />
    <!-- 新增操作 -->
    <el-row style="text-align: left">
      <el-button type="primary" @click="add">+新增部门</el-button>
    </el-row>
    <br />
    <el-dialog title="新增部门" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="部门名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addDept">确 定</el-button>
      </div>
    </el-dialog>
    <el-table
      :data="tableData"
      v-loading="loading"
      style="width: 60%; border: 1px solid #b3c0d1"
      :row-class-name="tableRowClassName"
    >
      <el-table-column prop="rowIndex" label="序号" width="150" align="center">
      </el-table-column>
      <el-table-column prop="name" label="部门" width="150" align="center">
      </el-table-column>
      <el-table-column
        prop="updateTime"
        label="最后操作时间"
        align="center"
        width="180"
      >
      </el-table-column>
      <el-table-column align="center" label="操作">
        <el-row slot-scope="scope">
          <el-button
            type="danger"
            icon="el-icon-delete"
            circle
            @click="del(scope.row)"
          ></el-button>
        </el-row>
      </el-table-column>
    </el-table>

    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      style="text-align: left"
    >
      <span>确定删除本条信息？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="determine">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "DeptView",
  methods: {
    tableRowClassName({ row, rowIndex }) {
      row.rowIndex = rowIndex;
      if (rowIndex % 2 === 0) {
        return "warning-row";
      } else if (rowIndex % 2 !== 0) {
        return "success-row";
      }
      return "";
    },
    add() {
      this.dialogFormVisible = true;
    },
    async determine() {
      // console.log(this.tableData[this.row])
      await axios.delete("http://localhost:8080/depts/" + this.tableData[this.row].id).then((res) => {
          if (res.data.msg === "success") {
            axios.get("http://localhost:8080/depts").then((res) => {
              this.tableData = res.data.data;
            });
          }
        });

      this.dialogVisible = false;
      this.$message({
        message: "删除成功",
        type: "success",
      });
    },
    del(row) {
      console.log(row.rowIndex)
      this.row = row.rowIndex
      this.dialogVisible = true
    },
    async addDept() {
      this.dialogFormVisible = false
      // console.log(this.form.name)
      await axios.post("http://localhost:8080/depts", {
          name: this.form.name
        }).then((res) => {
          if (res.data.msg === "success") {
            axios.get("http://localhost:8080/depts").then((res) => {
              this.tableData = res.data.data
            })
          }
        })
    }
  },
  data() {
    return {
      row: 1,
      dialogVisible: false,
      loading: false,
      flag: true,
      tableData: [
        {
          rowIndex: 1,
          id: 1,
          name: "学工部",
          updateTime: "2020-1-23",
        },
      ],
      dialogFormVisible: false,
      form: {
        name: "",
      },
      formLabelWidth: "120px",
    };
  },
  async created() {
    await axios.get("http://localhost:8080/depts").then((res) => {
      this.loading = true;
      setTimeout(() => {
        console.log(res.data);
        this.tableData = res.data.data;
        this.loading = false;
      }, 1000);
    });
  },
};
</script>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}

body {
  margin: 0;
}
</style>