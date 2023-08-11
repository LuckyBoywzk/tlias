<template>
  <div>
    <!-- 分割线 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>员工管理</el-breadcrumb-item>
    </el-breadcrumb>
    <br />
    <!-- 查询操作 -->
    <EmpQuery @select="submit"></EmpQuery>
    <!-- 新增删除按钮 -->
    <EmpButton :selection="multipleSelection" @select="sel" @send="insert"></EmpButton>
    <!-- 员工表格 -->
    <el-table
      :data="tableData"
      style="width: 100%; border: 1px solid #b3c0d1"
      :row-class-name="tableRowClassName"
      v-loading="loading"
      element-loading-text="拼命加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="name" label="姓名" width="180"> </el-table-column>
      <el-table-column prop="image" label="头像" width="180"> </el-table-column>
      <el-table-column prop="gender" label="性别" width="180">
      </el-table-column>
      <el-table-column prop="job" label="职位"> </el-table-column>
      <el-table-column prop="entrydate" label="入职日期"> </el-table-column>
      <el-table-column prop="updateTime" label="最后操作时间">
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-row>
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="edit"
            ></el-button>
            <el-button type="danger" icon="el-icon-delete" circle @click="del(scope.row)"></el-button>
          </el-row>
        </template>
      </el-table-column>
    </el-table>
    <br />
    <!-- 分页条 -->
    <PageCount
      :totalCount="total"
      @changePage="pageHandle"
      @page="page1"
    ></PageCount>
  </div>
</template>

<script>
import EmpQuery from "@/components/EmpQuery.vue"
import EmpButton from "@/components/EmpButton.vue"
import Bus from "@/utils/EventBus"
import PageCount from "@/components/PageCount.vue"
import axios from "axios"
export default {
  name: "EmpView",
  components: {
    EmpQuery,
    EmpButton,
    PageCount
  },
  methods: {
    async insert (val) {
      this.insertForm = val
      await axios.post("http://localhost:8080/Emps", {
        name: this.insertForm.name,
        image: this.insertForm.image,
        gender: this.insertForm.gender,
        job: this.insertForm.job
      }).then((res) => {
        if (res.data.msg === 'success') {
          axios.get("http://localhost:8080/Emps", {
            params: {
              page: this.page,
              pageSize: this.pageSize
          }
          }).then((res) => {
            this.total = res.data.data.total
            this.tableData = res.data.data.rows
          })
          this.$message({
            message: '恭喜你，插入成功',
            type: 'success'
          })
        }
      })
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
      // console.log(this.multipleSelection)
    },
    tableRowClassName (rowIndex) {
      if (rowIndex === 1) {
        return "warning-row"
      } else if (rowIndex === 3) {
        return "success-row"
      }
      return ""
    },
    edit() {
      Bus.$emit("edit", this.flag)
      // this.$message('此功能尚未开发');
    },
    // 修改每页展示记录数
    async pageHandle(size) {
      this.pageSize = size
      console.log(`每页${size}条`)
      await axios.get("http://localhost:8080/Emps", {
        params: {
          page: this.page,
          pageSize: this.pageSize
        }
      }).then((res) => {
        // console.log(res.data.data.total)
        // console.log(res.data.data.rows)
        this.total = res.data.data.total
        this.tableData = res.data.data.rows
      })
    },
    // 当前页面
    async page1 (page1) {
      this.page = page1;
      console.log(`当前第${page1}页`)
      await axios.get("http://localhost:8080/Emps", {
        params: {
          page: this.page,
          pageSize: this.pageSize
      }
      }).then((res) => {
        // console.log(res.data.data.total)
        // console.log(res.data.data.rows)
        this.total = res.data.data.total
        this.tableData = res.data.data.rows
      })
    },
    //查询数据
    async submit (val) {
      this.form = val
      console.log(this.form)
      await axios.get("http://localhost:8080/Emps", {
          params: {
            page: this.page,
            pageSize: this.pageSize,
            name: this.form.name,
            gender: this.form.gender,
            begin: this.form.begin,
            end: this.form.end
          }
        }).then((res) => {
          // console.log(res.data.data.total)
          // console.log(res.data.data.rows)
          this.total = res.data.data.total
          this.tableData = res.data.data.rows
        })
    },
    async sel () {
      await axios.get("http://localhost:8080/Emps", {
        params: {
        page: this.page,
        pageSize: this.pageSize
      },
      }).then((res) => {
        // console.log(res.data.data.total)
        // console.log(res.data.data.rows)
        this.total = res.data.data.total
        this.tableData = res.data.data.rows
      })
    },
    // 单个数据删除操作
    async del (row) {
      console.log(row.id)
      await axios.delete('http://localhost:8080/Emps/' + row.id).then((res) => {
        if (res.data.msg === 'success') {
          this.sel()  
          this.$message({
            message: '恭喜你，删除成功',
            type: 'success'
          })
        }
      })
    }
  },
  data () {
    return {
      total: 0,
      page: 1,
      pageSize: 5,
      flag: true,
      tableData: [
        /* {
          name: "张三",
          image: "1.jpg",
          gender: 1,
          job: "教师",
          entrydate: "2010-1-1",
          updateTime: "2020-2-12",
        } */
      ],
      insertForm:[],
      form:[],
      loading: false,
      multipleSelection: []
    }
  },

  // 加载数据
  async created () {
    await axios.get("http://localhost:8080/Emps", {
      params: {
        page: this.page,
        pageSize: this.pageSize
      }
    }).then((res) => {
      // console.log(res.data.data.total)
      // console.log(res.data.data.rows)
      this.loading = true
      setTimeout(() => {
        this.total = res.data.data.total
        this.tableData = res.data.data.rows
        this.loading = false
      }, 1000)
    });
  }
}
</script>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>