package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class MainAmazon {

    private static List<Entry<String, Integer>> sortKeywordMap(
            Map<String, Integer> keywordMap) {
        List<Entry<String, Integer>> arrayList = new ArrayList<Entry<String, Integer>>(
                keywordMap.entrySet());
        Collections.sort(arrayList, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> e1,
                               Entry<String, Integer> e2) {
                if (e2.getValue() == e1.getValue()) {
                    return (e1.getKey()).compareTo(e2.getKey());
                }
                return (e2.getValue()).compareTo(e1.getValue());
            }
        });
        return arrayList;
    }

    private static List<Entry<String, Integer>> calculate(String input) {
        Map<String, Integer> map = new HashMap<>();
        for (int index = 0; index < input.length(); index++) {
            String currString = input.substring(index, index + 1);
            ;
            if (map.containsKey(currString)) {
                int currValue = map.get(currString);
                currValue++;
                map.put(currString, currValue);
            } else {
                map.put(currString, 1);
            }
        }

        List<Entry<String, Integer>> list = sortKeywordMap(map);

        return list;
    }

    public static int min(List<Integer> input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        Iterator<Integer> iterator = input.iterator();
        int min = iterator.next();
        while (iterator.hasNext()) {
            int curr = iterator.next();
            min = Math.min(curr, min);
        }
        return min;
    }

    static int distance(int x, int y, int currX, int currY) {
        return Math.abs(x - currX) + Math.abs(y - currY);
    }

    static int[][] getLockerDistanceGrid(int cityLength, int cityWidth, int[] lockerXCoordinates, int[] lockerYCoordinates) throws Exception {
        if (cityLength > 9 || cityLength < 1) {
            throw new Exception("out of range");
        }
        if (cityWidth > 9 || cityWidth < 1) {
            throw new Exception("out of range");
        }
        if (lockerXCoordinates.length != lockerYCoordinates.length) {
            throw new Exception("array length mismatch");
        }
        Map<String, List<Integer>> coorToDistances = new HashMap<>();
        for (int n = 0; n < lockerXCoordinates.length; n++) {
            for (int i = 0; i < cityLength; i++) {
                for (int j = 0; j < cityWidth; j++) {
                    String key = String.format("%d:%d", i, j);
                    if (coorToDistances.containsKey(key)) {
                        coorToDistances.get(key).add(distance(i, j, lockerXCoordinates[n], lockerYCoordinates[n]));
                    } else {
                        List<Integer> distances = new ArrayList<>(lockerXCoordinates.length);
                        distances.add(distance(i, j, lockerXCoordinates[n], lockerYCoordinates[n]));
                        coorToDistances.put(key, distances);
                    }
                }
            }
        }
        int[][] result = new int[cityLength][cityWidth];
        for (int i = 0; i < cityLength; i++) {
            for (int j = 0; j < cityWidth; j++) {
                String key = String.format("%d:%d", i, j);
                if (coorToDistances.containsKey(key)) {
                    List<Integer> distances = coorToDistances.get(key);
                    int distance = min(distances);
                    result[i][j] = distance;
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }

    public List<String> getDirectFriendsForUser(String user) {
        return null;
    }

    public List<String> getAttendedCoursesForUser(String user) {
        return null;
    }

    public List<String> getRankedCourses(String user) {
        Map<String, Integer> courseToRank = new HashMap<>();
        List<String> attenedCourses = getAttendedCoursesForUser(user);
        for (String directUser : getDirectFriendsForUser(user)) {
            for (String directUserAttendedCourse : getAttendedCoursesForUser(directUser)) {
                if (attenedCourses.contains(directUserAttendedCourse)) {
                    continue;
                }
                if (courseToRank.containsKey(directUserAttendedCourse)) {
                    courseToRank.put(directUserAttendedCourse, courseToRank.get(directUserAttendedCourse) + 1);
                } else {
                    courseToRank.put(directUserAttendedCourse, 1);
                }
            }

            for (String secondUser : getDirectFriendsForUser(directUser)) {
                for (String secondUserAttendedCourse : getAttendedCoursesForUser(secondUser)) {
                    if (attenedCourses.contains(secondUserAttendedCourse)) {
                        continue;
                    }
                    if (courseToRank.containsKey(secondUserAttendedCourse)) {
                        courseToRank.put(secondUserAttendedCourse, courseToRank.get(secondUserAttendedCourse) + 1);
                    } else {
                        courseToRank.put(secondUserAttendedCourse, 1);
                    }
                }
            }
        }

        Map<Integer, List<String>> rankToCourses = new TreeMap<>(new Comparator() {
            public int compare(Object o1, Object o2) {
                if (o1 == null || o2 == null)
                    return 0;
                return -((Integer) o1).compareTo((Integer) o2);
            }
        });
        for (Map.Entry<String, Integer> entry : courseToRank.entrySet()) {
            String course = entry.getKey();
            Integer rank = entry.getValue();
            if (rankToCourses.containsKey(rank)) {
                rankToCourses.get(rank).add(course);
            } else {
                List<String> courses = new LinkedList<>();
                courses.add(course);
                rankToCourses.put(rank, courses);
            }
        }

        List<String> result = new LinkedList<>();
        for (List<String> courses : rankToCourses.values()) {
            for (String course : courses) {
                result.add(course);
            }
        }

        return result;
    }

    /*
    The answer is O(N*N*K), and the explanation is as follows:

    First of all, I would like to assume number of possible person (friends) is N, and number of possible courses is K.

    Then, I would like to divide my code into three chunks. Chunk 1 is to calculate Map courseToRank, Chunk 2 is to calculate Map rankToCourses, Chunk 3 is to calculate List result. For chunk 1, the time complexity is O(N*N*K) as we go through 2 layers of direct friend, and one layer of attended course. For chunk 2, it is O(N) as we only go through the Map courseToRank. For chunk 3, it is O(N*K) as we go the Map rankToCourses, which go through a list of courses internally.

    So, the result should be O(N*N*K) + O(N) + O(N*K), which is O(N*N*K).
     */

    public static void main(String[] args) {

        StringBuilder s = new StringBuilder();
        s.append("asdf");
        s.toString();
        System.out.println(s.toString());

        // input
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));

        // read
        String input = "";
        try {
            input = stdin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Entry<String, Integer>> list = calculate(input);

        // ouput
        for (Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey());
        }
    }

}
