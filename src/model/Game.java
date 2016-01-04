package model;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

import model.game.Field;
import model.game.Player;
import model.game.Rule;
import model.game.Team;
import model.game.field.Map;
import model.game.field.dynamic.Turf;
import model.game.field.map.MapBlock;

public class Game {
	private Vector<Team> teams;
	private int time;
	private Field field;
	private Rule rule;
	// private String fieldFilePath = \"C:/Users/rose/Google
	// ¶³ºÝµwºÐ/Eclipse_Java/ncu2015-345_test/src/file/field.txt\";
	private String fieldString = "{\"map\":{\"size\":{\"width\":32,\"height\":32},\"mapBlocks\":[[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":0},\"ID\":0}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":32,\"y\":0},\"ID\":1}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":64,\"y\":0},\"ID\":2}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":96,\"y\":0},\"ID\":3}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":128,\"y\":0},\"ID\":4}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":160,\"y\":0},\"ID\":5}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":192,\"y\":0},\"ID\":6}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":224,\"y\":0},\"ID\":7}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":256,\"y\":0},\"ID\":8}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":288,\"y\":0},\"ID\":9}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":320,\"y\":0},\"ID\":10}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":352,\"y\":0},\"ID\":11}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":384,\"y\":0},\"ID\":12}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":416,\"y\":0},\"ID\":13}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":448,\"y\":0},\"ID\":14}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":480,\"y\":0},\"ID\":15}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":512,\"y\":0},\"ID\":16}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":544,\"y\":0},\"ID\":17}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":576,\"y\":0},\"ID\":18}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":0},\"ID\":19}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":32},\"ID\":40}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":64,\"y\":32},\"ID\":76}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":32},\"ID\":58}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":64},\"ID\":41}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":64},\"ID\":59}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":96},\"ID\":42}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":96},\"ID\":60}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":128},\"ID\":43}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":128},\"ID\":61}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":160},\"ID\":44}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":160},\"ID\":62}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":192},\"ID\":45}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":192},\"ID\":63}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":224},\"ID\":46}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":224},\"ID\":64}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":256},\"ID\":47}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":256},\"ID\":65}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":288},\"ID\":48}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":288},\"ID\":66}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":320},\"ID\":49}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":320},\"ID\":67}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":352},\"ID\":50}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":352},\"ID\":68}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":384},\"ID\":51}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":384},\"ID\":69}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":416},\"ID\":52}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":416},\"ID\":70}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":448},\"ID\":53}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":448},\"ID\":71}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":480},\"ID\":54}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":480},\"ID\":72}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":512},\"ID\":55}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":512},\"ID\":73}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":544},\"ID\":56}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":544},\"ID\":74}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":576},\"ID\":57}],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":576},\"ID\":75}],\"type\":1}],[{\"objects\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":608},\"ID\":20}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":32,\"y\":608},\"ID\":21}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":64,\"y\":608},\"ID\":22}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":96,\"y\":608},\"ID\":23}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":128,\"y\":608},\"ID\":24}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":160,\"y\":608},\"ID\":25}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":192,\"y\":608},\"ID\":26}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":224,\"y\":608},\"ID\":27}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":256,\"y\":608},\"ID\":28}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":288,\"y\":608},\"ID\":29}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":320,\"y\":608},\"ID\":30}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":352,\"y\":608},\"ID\":31}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":384,\"y\":608},\"ID\":32}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":416,\"y\":608},\"ID\":33}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":448,\"y\":608},\"ID\":34}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":480,\"y\":608},\"ID\":35}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":512,\"y\":608},\"ID\":36}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":544,\"y\":608},\"ID\":37}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":576,\"y\":608},\"ID\":38}],\"type\":1},{\"objects\":[{\"breakable\":true,\"location\":{\"x\":608,\"y\":608},\"ID\":39}],\"type\":1}]]},\"characters\":[],\"bullets\":[],\"obstacles\":[{\"breakable\":true,\"location\":{\"x\":0,\"y\":0},\"ID\":0},{\"breakable\":true,\"location\":{\"x\":32,\"y\":0},\"ID\":1},{\"breakable\":true,\"location\":{\"x\":64,\"y\":0},\"ID\":2},{\"breakable\":true,\"location\":{\"x\":96,\"y\":0},\"ID\":3},{\"breakable\":true,\"location\":{\"x\":128,\"y\":0},\"ID\":4},{\"breakable\":true,\"location\":{\"x\":160,\"y\":0},\"ID\":5},{\"breakable\":true,\"location\":{\"x\":192,\"y\":0},\"ID\":6},{\"breakable\":true,\"location\":{\"x\":224,\"y\":0},\"ID\":7},{\"breakable\":true,\"location\":{\"x\":256,\"y\":0},\"ID\":8},{\"breakable\":true,\"location\":{\"x\":288,\"y\":0},\"ID\":9},{\"breakable\":true,\"location\":{\"x\":320,\"y\":0},\"ID\":10},{\"breakable\":true,\"location\":{\"x\":352,\"y\":0},\"ID\":11},{\"breakable\":true,\"location\":{\"x\":384,\"y\":0},\"ID\":12},{\"breakable\":true,\"location\":{\"x\":416,\"y\":0},\"ID\":13},{\"breakable\":true,\"location\":{\"x\":448,\"y\":0},\"ID\":14},{\"breakable\":true,\"location\":{\"x\":480,\"y\":0},\"ID\":15},{\"breakable\":true,\"location\":{\"x\":512,\"y\":0},\"ID\":16},{\"breakable\":true,\"location\":{\"x\":544,\"y\":0},\"ID\":17},{\"breakable\":true,\"location\":{\"x\":576,\"y\":0},\"ID\":18},{\"breakable\":true,\"location\":{\"x\":608,\"y\":0},\"ID\":19},{\"breakable\":true,\"location\":{\"x\":0,\"y\":608},\"ID\":20},{\"breakable\":true,\"location\":{\"x\":32,\"y\":608},\"ID\":21},{\"breakable\":true,\"location\":{\"x\":64,\"y\":608},\"ID\":22},{\"breakable\":true,\"location\":{\"x\":96,\"y\":608},\"ID\":23},{\"breakable\":true,\"location\":{\"x\":128,\"y\":608},\"ID\":24},{\"breakable\":true,\"location\":{\"x\":160,\"y\":608},\"ID\":25},{\"breakable\":true,\"location\":{\"x\":192,\"y\":608},\"ID\":26},{\"breakable\":true,\"location\":{\"x\":224,\"y\":608},\"ID\":27},{\"breakable\":true,\"location\":{\"x\":256,\"y\":608},\"ID\":28},{\"breakable\":true,\"location\":{\"x\":288,\"y\":608},\"ID\":29},{\"breakable\":true,\"location\":{\"x\":320,\"y\":608},\"ID\":30},{\"breakable\":true,\"location\":{\"x\":352,\"y\":608},\"ID\":31},{\"breakable\":true,\"location\":{\"x\":384,\"y\":608},\"ID\":32},{\"breakable\":true,\"location\":{\"x\":416,\"y\":608},\"ID\":33},{\"breakable\":true,\"location\":{\"x\":448,\"y\":608},\"ID\":34},{\"breakable\":true,\"location\":{\"x\":480,\"y\":608},\"ID\":35},{\"breakable\":true,\"location\":{\"x\":512,\"y\":608},\"ID\":36},{\"breakable\":true,\"location\":{\"x\":544,\"y\":608},\"ID\":37},{\"breakable\":true,\"location\":{\"x\":576,\"y\":608},\"ID\":38},{\"breakable\":true,\"location\":{\"x\":608,\"y\":608},\"ID\":39},{\"breakable\":true,\"location\":{\"x\":0,\"y\":32},\"ID\":40},{\"breakable\":true,\"location\":{\"x\":0,\"y\":64},\"ID\":41},{\"breakable\":true,\"location\":{\"x\":0,\"y\":96},\"ID\":42},{\"breakable\":true,\"location\":{\"x\":0,\"y\":128},\"ID\":43},{\"breakable\":true,\"location\":{\"x\":0,\"y\":160},\"ID\":44},{\"breakable\":true,\"location\":{\"x\":0,\"y\":192},\"ID\":45},{\"breakable\":true,\"location\":{\"x\":0,\"y\":224},\"ID\":46},{\"breakable\":true,\"location\":{\"x\":0,\"y\":256},\"ID\":47},{\"breakable\":true,\"location\":{\"x\":0,\"y\":288},\"ID\":48},{\"breakable\":true,\"location\":{\"x\":0,\"y\":320},\"ID\":49},{\"breakable\":true,\"location\":{\"x\":0,\"y\":352},\"ID\":50},{\"breakable\":true,\"location\":{\"x\":0,\"y\":384},\"ID\":51},{\"breakable\":true,\"location\":{\"x\":0,\"y\":416},\"ID\":52},{\"breakable\":true,\"location\":{\"x\":0,\"y\":448},\"ID\":53},{\"breakable\":true,\"location\":{\"x\":0,\"y\":480},\"ID\":54},{\"breakable\":true,\"location\":{\"x\":0,\"y\":512},\"ID\":55},{\"breakable\":true,\"location\":{\"x\":0,\"y\":544},\"ID\":56},{\"breakable\":true,\"location\":{\"x\":0,\"y\":576},\"ID\":57},{\"breakable\":true,\"location\":{\"x\":608,\"y\":32},\"ID\":58},{\"breakable\":true,\"location\":{\"x\":608,\"y\":64},\"ID\":59},{\"breakable\":true,\"location\":{\"x\":608,\"y\":96},\"ID\":60},{\"breakable\":true,\"location\":{\"x\":608,\"y\":128},\"ID\":61},{\"breakable\":true,\"location\":{\"x\":608,\"y\":160},\"ID\":62},{\"breakable\":true,\"location\":{\"x\":608,\"y\":192},\"ID\":63},{\"breakable\":true,\"location\":{\"x\":608,\"y\":224},\"ID\":64},{\"breakable\":true,\"location\":{\"x\":608,\"y\":256},\"ID\":65},{\"breakable\":true,\"location\":{\"x\":608,\"y\":288},\"ID\":66},{\"breakable\":true,\"location\":{\"x\":608,\"y\":320},\"ID\":67},{\"breakable\":true,\"location\":{\"x\":608,\"y\":352},\"ID\":68},{\"breakable\":true,\"location\":{\"x\":608,\"y\":384},\"ID\":69},{\"breakable\":true,\"location\":{\"x\":608,\"y\":416},\"ID\":70},{\"breakable\":true,\"location\":{\"x\":608,\"y\":448},\"ID\":71},{\"breakable\":true,\"location\":{\"x\":608,\"y\":480},\"ID\":72},{\"breakable\":true,\"location\":{\"x\":608,\"y\":512},\"ID\":73},{\"breakable\":true,\"location\":{\"x\":608,\"y\":544},\"ID\":74},{\"breakable\":true,\"location\":{\"x\":608,\"y\":576},\"ID\":75},{\"breakable\":true,\"location\":{\"x\":64,\"y\":32},\"ID\":76}],\"turfs\":[{\"teamID\":-1,\"timeAtOccupy\":-1,\"location\":{\"x\":32,\"y\":576},\"ID\":77},{\"teamID\":-1,\"timeAtOccupy\":-1,\"location\":{\"x\":320,\"y\":320},\"ID\":78},{\"teamID\":-1,\"timeAtOccupy\":-1,\"location\":{\"x\":576,\"y\":32},\"ID\":79}]}";

	public Game(AtomicInteger atomicInteger) {
		Dimension size= new Dimension(40, 40);
		MapGen mp = new MapGen(atomicInteger, size);
		// try {
		// field = readFieldFile();
		field = new Field();
		MapBlock[][] mapBlocks = new MapBlock[size.height][size.width];
		for (int y = 0; y < size.height; y++) {
			for (int x = 0; x < size.width; x++) {
				mapBlocks[y][x] = new MapBlock(1);
			}
		}
		field.setMap(new Map(size, mapBlocks));
		field = mp.createField(field, field.getMap());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		teams = new Vector<Team>();
		teams.add(new Team(1));
		teams.add(new Team(2));
		rule = new Rule(this);
		// System.out.println("[Game] : " + (field.getMap().getMapBlock(0,
		// 0).getDynamicObjectList().get(0) instanceof Obstacle));
	}

	private Field readFieldFile() throws IOException {
		// File f = new File(fieldFilePath);
		// FileReader fr = new FileReader(f);
		// BufferedReader br = new BufferedReader(fr);
		// String s = \"\";
		// StringBuffer sb = new StringBuffer();
		// while ((s = br.readLine()) != null) {
		// sb.append(s);
		// }
		// br.close();
		Gson gson = new Gson();
		// return gson.fromJson(sb.toString(), Field.class);
		return gson.fromJson(fieldString, Field.class);
	}

	public void setMap(Map map) {
		field.setMap(map);
	}

	public Vector<Team> getTeams() {
		return teams;
	}

	public synchronized Team getTeam(int ID) {
		synchronized (teams) {
			for (int i = 0; i < teams.size(); i++) {
				if (teams.get(i).getID() == ID) {
					return teams.get(i);
				}
			}
			return null;
		}
	}

	public synchronized Player getPlayer(int ID) {
		synchronized (teams) {
			for (int i = 0; i < teams.size(); i++) {
				if (teams.get(i).getPlayer(ID) != null) {
					return teams.get(i).getPlayer(ID);
				}
			}
			return null;
		}
	}

	public synchronized void addTeam(Team team) {
		synchronized (teams) {
			teams.add(team);
		}
	}

	public synchronized void remove(Team team) {
		synchronized (teams) {
			teams.remove(team);
		}
	}

	public synchronized void setField(Field field) {
		this.field = field;
	}

	public Field getField() {
		return field;
	}

	public Rule getRule() {
		return rule;
	}

	public synchronized void setRule(Rule rule) {
		this.rule = rule;
	}

	public int getTime() {
		return time;
	}

	public Vector<Player> getPlayerList() {
		Vector<Player> tmpList = new Vector<>();
		for (int i = 0; i < getTeams().size(); i++) {
			tmpList.addAll(getTeams().get(i).getPlayerList());
		}
		return tmpList;
	}

	public synchronized void setTime(int time) {
		this.time = time;
	}

	public synchronized Turf getTurf(int id) {
		synchronized (field.getTurfs()) {
			for (int i = 0; i < field.getTurfs().size(); i++) {
				if (field.getTurfs().get(i).getID() == id) {
					return field.getTurfs().get(i);
				}
			}
			return null;
		}
	}
}
