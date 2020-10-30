package com.ys.appler.service;


import com.ys.appler.mapper.BoardMapper;



import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest extends TestCase {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardMapper boardMapper;


}