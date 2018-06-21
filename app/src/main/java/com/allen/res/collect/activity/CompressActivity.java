package com.allen.res.collect.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.allen.res.collect.R;
import com.allen.res.tools.compresss.CompressUtils;

public class CompressActivity extends AppCompatActivity {

    private String compressSource = "{\"id\":\"00001\", \"name\":\"张三\"}";

    private TextView tvCompressSource, tvCompressResult, tvDecodeResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compress);

        tvCompressSource = findViewById(R.id.tv_compress_source);
        tvCompressResult = findViewById(R.id.tv_compress_result);
        tvDecodeResult = findViewById(R.id.tv_decode_result);

        tvCompressSource.setText(compressSource);
    }

    public void btnCompress(View view) {
        tvCompressResult.setText(CompressUtils.compress(compressSource));
    }

    public void btnDecode(View view) {
        String compressResult = tvCompressResult.getText().toString().trim();
        tvDecodeResult.setText(CompressUtils.uncompress(compressResult));
    }
}
