/**
 * This is the runner class to simulate a Premier League season.
 */

import java.io.*;
import java.util.*;

public class TeamTester
{

	public static void main(String[] args)
	{
		new TeamTester();
	}

	public TeamTester()
	{
		List<Team> teams = new ArrayList<Team>();
		List<Match> matches = new ArrayList<Match>();
		List<ArrayList<Match>> allMatches = new ArrayList<ArrayList<Match>>();
		Match currentMatch = null;

		// Add teams and give them a ranking (somewhat arbitrary)
		teams.add(new Team("Arsenal F.C.", "Emirates", 1));
		teams.add(new Team("Chelsea F.C.", "Stamford Bridge", 1));
		teams.add(new Team("Manchester United", "Old Trafford", 1));
		teams.add(new Team("Manchester City", "Etihad Stadium", 1));
		teams.add(new Team("Liverpool", "Anfield", 1));
		teams.add(new Team("QPR", "Loftus Road", 4));
		teams.add(new Team("Tottenham Hotspurs", "White Hart Lane", 2));
		teams.add(new Team("Swansea City", "Liberty Stadium", 3));
		teams.add(new Team("Everton", "Goodison Park", 2));
		teams.add(new Team("Aston Villa", "Villa Park", 3));
		teams.add(new Team("Wigan Athletic", "DW Stadium", 4));
		teams.add(new Team("Fulham", "Craven Cottage", 4));
		teams.add(new Team("West Brom", "The Hawthorns", 3));
		teams.add(new Team("Stoke City", "Britannia Stadium", 3));
		teams.add(new Team("Sunderland", "Stadium of Light", 3));
		teams.add(new Team("Norwich City", "Carrow Road", 3));
		teams.add(new Team("Newcastle United", "St James' Park", 2));
		teams.add(new Team("Reading", "Madejski Stadium", 4));
		teams.add(new Team("Southampton", "St Mary's Stadium", 3));
		teams.add(new Team("West Ham United", "Boleyn Ground", 3));

		// Create matches for the first half of the Premier League
		System.out.println("First Half of the Premier League:\n");
		for (int j = 0; j < 19; j++)
		{
			Collections.shuffle(teams);
			if (j > 0)
				while (!listIsUnique(teams, allMatches))
					Collections.shuffle(teams);

			System.out.println("Week " + (j + 1) + ":");
			for (int i = 0; i < teams.size() / 2; i++)
			{
				matches.add(new Match(teams.get(i), teams.get(i + teams.size() / 2)));
				currentMatch = matches.get(i);
				Match.playMatchAccurately(currentMatch);
				currentMatch.print();
			}
			allMatches.add((ArrayList<Match>) ((ArrayList<Match>) matches).clone());
			matches.clear();
		}

		// Create matches for the second half of the season (we take the matches from the first half and reverse home/away)
		for (ArrayList<Match> week : allMatches)
			for (Match m : week)
			{
				m.reset();
				m.reverseTeams();
			}

		Collections.shuffle(allMatches);
		System.out.println("\nSecond Half of the Premier League:\n");
		for (int j = 0; j < 19; j++)
		{
			System.out.println("Week " + (j + 20) + ":");

			for (int i = 0; i < allMatches.get(0).size(); i++)
			{
				currentMatch = allMatches.get(j).get(i);
				Match.playMatchAccurately(currentMatch);
				currentMatch.print();
			}
		}
		
		// Display the table at the end of the season
		displayTable(teams);

	}

	/**
	 * Displays the list of teams in order of points
	 * 
	 * @param theTeams
	 *            list of teams
	 */
	private void displayTable(List<Team> theTeams)
	{
		System.out.println();
		Collections.sort(theTeams);
		Collections.reverse(theTeams);
		int i = 1;

		System.out.printf("%5s. %-20s %3s %3s %3s %3s %4s %4s %4s %5s%n", "Pos", "Team", "GP", "W", "D", "L", "GS",
				"GA", "GD", "PTS");
		for (Team t : theTeams)
		{
			System.out.printf("%5d. %-20s %3d %3d %3d %3d %4d %4d %4d %5d%n", i, t, t.getGamesPlayed(), t.getGamesWon(),
					t.getGamesDrawn(), t.getGamesLost(), t.getGoalsScored(), t.getGoalsConceded(),
					t.getGoalDifference(), t.getPoints());
			i++;
		}
	}

	/**
	 * Determines whether shuffle of teams is unique
	 * 
	 * @param teams
	 *            the list of teams after shuffle
	 * @param allMatches
	 *            the list of all matches that stores the past shuffles of teams
	 * @return true if the shuffle is unique
	 */
	private boolean listIsUnique(List<Team> teams, List<ArrayList<Match>> allMatches)
	{
		List<Team> teamsFromMatches = new ArrayList<Team>();

		for (ArrayList<Match> m : allMatches)
		{
			for (int i = 0; i < teams.size() / 2; i++)
				teamsFromMatches.add(m.get(i).getHometeam());
			for (int i = 0; i < teams.size() / 2; i++)
				teamsFromMatches.add(m.get(i).getAwayteam());

			if (teamsFromMatches.equals(teams))
			{
				teamsFromMatches.clear();
				return false;
			}
			teamsFromMatches.clear();
		}
		return true;
	}

}
