<template>

    <el-form :model="form" >
        <el-form-item label="历史病案采集模板">
            <el-upload ref="upload" action="http://localhost:9090/files/upload" :on-success="filesUploadSuccess">
                <el-button type="primary">点击上传</el-button>

            </el-upload>
        </el-form-item>
    </el-form>

</template>

<script>

import request from "@/utils/request";
import 'amfe-flexible';
import { ref } from 'vue';

export default {
  name: 'UploadFile',
  components: {

  },
  data() {
    return {
        search:'',
        currentPage4: 1,
        pageSize: 10,
        total:0,

    }
  },
  created() {

  },
  methods:{

  },
  setup() {
      const fileList = ref([
          {
              url: 'https://fastly.jsdelivr.net/npm/@vant/assets/leaf.jpeg',
              status: 'uploading',
              message: '上传中...',
          },
          {
              url: 'https://fastly.jsdelivr.net/npm/@vant/assets/tree.jpeg',
              status: 'failed',
              message: '上传失败',
          },
      ]);

      const afterRead = (file) => {
          file.status = 'uploading';
          file.message = '上传中...';
          localhost:9090/upload
          setTimeout(() => {
              file.status = 'failed';
              file.message = '上传失败';
          }, 1000);
      };

      return {
          fileList,
          afterRead,
      };
  },
}


</script>
<style>
    .van-field{
        width: 9rem;
    }
</style>