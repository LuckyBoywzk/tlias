<template>
  <div>
    <!-- 分页条 -->
    <div class="block">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="page"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalCount">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    totalCount: {
      type: Number,
      /* required: true,
      default: 200,
      validator(value) {

      } */
    }
  },
  methods: {
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`);
      this.$emit('changePage', val)
    },
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`);
      this.$emit('page', val)
    }
  },
  data() {
    return {
      page: 1,
      total: 100,
      pageSize: 5
    };
  }
}
</script>

<style>
.block {
  text-align: left;
}
</style>