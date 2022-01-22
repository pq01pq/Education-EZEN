package baseball;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

import manage.Manage;

public class ManagePlayer implements Manage {
	// key : team, value : (name, income)
	private Hashtable<String, Hashtable<String, Integer>> teams;
	Scanner scan;
	Enumeration<String> en;

	public ManagePlayer() {
		teams = new Hashtable<>();
		scan = new Scanner(System.in);
	}

	@Override
	public void insert() {
		System.out.println("구단 목록 [LG, SSG, KIA, 삼성, 롯데, NC, 한화, 두산, KT, 키움]");
		// 고정값은 배열로 넣는게 좋음
		System.out.print("구단: ");
		String team = scan.next().toUpperCase();
		System.out.print("이름: ");
		String name = scan.next();
		System.out.print("연봉: ");
		int income = scan.nextInt();
		
		_insertPlayer(team, name, income);
	}
	
	public void _insertPlayer(String team, String name, int income) {
		if(teams.containsKey(team)) {
			teams.get(team).put(name, income);
		} else {
			Hashtable<String, Integer> players = new Hashtable<>();
			players.put(name, income);
			teams.put(team, players);
		}
	}

	@Override
	public void view() {
		System.out.print("조회할 구단: ");
		String team = scan.next().toUpperCase();
		
		if(teams.containsKey(team)) {
			Hashtable<String, Integer> players = teams.get(team);
			en = players.keys();
			while(en.hasMoreElements()) {
				String name = en.nextElement();
				System.out.println(name + " (연봉 " + players.get(name) + ")");
			}
		}
	}

	@Override
	public void delete() {
		System.out.print("삭제할 선수의 소속 구단: ");
		String team = scan.next().toUpperCase();
		System.out.print("삭제할 선수 이름: ");
		String name = scan.next();
		
		if(_findPlayer(team, name)) {
			_deletePlayer(team, name);
		}
	}
	
	public void _deletePlayer(String team, String name){
		Hashtable<String, Integer> players = teams.get(team);
		players.remove(name);
		
		if(players.isEmpty()) {
			teams.remove(team);
		}
	}
	
	public boolean _findPlayer(String team, String name) {
		if(teams.containsKey(team)) {
			if(teams.get(team).contains(name)) {
				return true;
			} else {
				System.out.println("선수 없음");
			}
		} else {
			System.out.println("구단 없음");
		}
		return false;
	}

	@Override
	public void modify() {
		System.out.print("이적할 선수의 소속 구단: ");
		String preTeam = scan.next().toUpperCase();
		System.out.print("이적할 선수 이름: ");
		String name = scan.next();
		if(_findPlayer(preTeam, name)) {
			System.out.println("이적할 구단: ");
			String nextTeam = scan.next().toUpperCase();
			int income = teams.get(preTeam).get(name);
			_insertPlayer(nextTeam, name, income);
			_deletePlayer(preTeam, name);
			System.out.println("이적 성공");
		} else {
			System.out.println("이적 실패");
		}
	}

}
