package com.example.UI.service;

import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class SendDataService<T> {
    private T data; //数据
}