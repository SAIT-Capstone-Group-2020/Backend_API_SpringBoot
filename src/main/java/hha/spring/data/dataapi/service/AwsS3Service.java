package hha.spring.data.dataapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import sun.net.www.http.HttpClient;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URI;

@Service
public class AwsS3Service {
    private String bucketName;
    private Region region;
    private String aws_access_key_id;
    private String aws_secret_access_key;
    private S3Client s3Client;
    private String regionName;

    public AwsS3Service(@Value("${AWS_ACCESS_KEY_ID}") String aws_access_key_id, @Value("${AWS_SECRET_ACCESS_KEY}") String aws_secret_access_key,
                        @Value("${AWS_S3_BUCKET}") String aws_s3_bucket,
                        @Value("${AWS_S3_REGION}") String aws_s3_region) {
        this.bucketName = aws_s3_bucket;
        this.aws_access_key_id = aws_access_key_id;
        this.aws_secret_access_key = aws_secret_access_key;
        this.region = Region.of(aws_s3_region);
        this.regionName = aws_s3_region;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getRegionName() {
        return regionName;
    }

    @PostConstruct
    public void init() {
        s3Client = S3Client.builder().region(region).build();
    }

    public void upload(String key, byte[] data) {
        final PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(key).build();
        s3Client.putObject(request, RequestBody.fromBytes(data));
    }

    public void uploadFile(String key, File file) {
        final PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(key).build();
        s3Client.putObject(request, RequestBody.fromFile(file));
    }


}
