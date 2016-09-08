package test;

import java.util.*;

public class MainAmazon {

    /////////////////////////////////////////////////////////
    //test 1
    /////////////////////////////////////////////////////////

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

    static boolean check(int cityLength, int cityWidth, int lockerXCoordinate, int lockerYCoordinate) {
        if (lockerXCoordinate < 0 || lockerYCoordinate < 0 || lockerXCoordinate >= cityLength || lockerYCoordinate >= cityWidth) {
            return false;
        }
        return true;
    }


    static Set<int[]> neighbours(int cityLength, int cityWidth, int lockerXCoordinate, int lockerYCoordinate, int distance) {
        Set<int[]> results = new HashSet<>();
        int currX;
        int currY;
        for (int i = -distance; i <= distance; i++) {
            int j = distance - Math.abs(i);
            if (check(cityLength, cityWidth, lockerXCoordinate + i, lockerYCoordinate + j)) {
                results.add(new int[]{lockerXCoordinate + i, lockerYCoordinate + j});
            }
            j = -j;
            if (check(cityLength, cityWidth, lockerXCoordinate + i, lockerYCoordinate + j)) {
                results.add(new int[]{lockerXCoordinate + i, lockerYCoordinate + j});
            }
        }
        return results;
    }

    // TODO: better stop criteria
    static int[][] getLockerDistanceGrid2(int cityLength, int cityWidth, int[] lockerXCoordinates, int[] lockerYCoordinates) throws Exception {
        if (cityLength > 9 || cityLength < 1) {
            throw new Exception("out of range");
        }
        if (cityWidth > 9 || cityWidth < 1) {
            throw new Exception("out of range");
        }
        if (lockerXCoordinates.length != lockerYCoordinates.length) {
            throw new Exception("array length mismatch");
        }
        int[][] result = new int[cityLength][cityWidth];
        int[][] check = new int[cityLength][cityWidth];
        int total = 0;
        int distance = 0;
        while (total < cityLength * cityWidth) {
            for (int n = 0; n < lockerXCoordinates.length; n++) {
                int currX = lockerXCoordinates[n];
                int currY = lockerYCoordinates[n];
                Set<int[]> neighbours = neighbours(cityLength, cityWidth, currX, currY, distance);
                for (int[] neighbour : neighbours) {
                    if (check[neighbour[0]][neighbour[1]] == 0) {
                        check[neighbour[0]][neighbour[1]] = 1;
                        result[neighbour[0]][neighbour[1]] = distance;
                        total++;
                    }
                }
            }
            distance++;
        }
        return result;
    }

    /////////////////////////////////////////////////////////
    //test 2
    /////////////////////////////////////////////////////////

    /*
    The answer is O(N*N*K), and the explanation is as follows:

    First of all, I would like to assume number of possible person (friends) is N, and number of possible courses is K.

    Then, I would like to divide my code into three chunks. Chunk 1 is to calculate Map courseToRank, Chunk 2 is to calculate Map rankToCourses, Chunk 3 is to calculate List result. For chunk 1, the time complexity is O(N*N*K) as we go through 2 layers of direct friend, and one layer of attended course. For chunk 2, it is O(N) as we only go through the Map courseToRank. For chunk 3, it is O(N*K) as we go the Map rankToCourses, which go through a list of courses internally.

    So, the result should be O(N*N*K) + O(N) + O(N*K), which is O(N*N*K).
     */

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

        @SuppressWarnings("unchecked")
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

    public static void main(String[] args) {

        // small test for stringbuilder
        System.out.println("//////////////////////////////////////////");
        StringBuilder s = new StringBuilder();
        s.append("asdf");
        System.out.println(s.toString());
        // small test for array
        System.out.println("//////////////////////////////////////////");
        int[][] results = new int[2][2];
        for (int[] rr : results) {
            for (int rrr : rr) {
                System.out.print(rrr);
                System.out.print(" ");
            }
            System.out.print("\n");
        }

        //run test 1
        System.out.println("//////////////////////////////////////////");
        int cityLength = 9;
        int cityWidth = 9;
        int[] lockerXCoordinates = new int[]{1, 8};
        int[] lockerYCoordinates = new int[]{1, 8};
        try {
            int[][] r = getLockerDistanceGrid(cityLength, cityWidth, lockerXCoordinates, lockerYCoordinates);
            for (int[] rr : r) {
                for (int rrr : rr) {
                    System.out.print(rrr);
                    System.out.print(" ");
                }
                System.out.print("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("//////////////////////////////////////////");

        try {
            int[][] r = getLockerDistanceGrid2(cityLength, cityWidth, lockerXCoordinates, lockerYCoordinates);
            for (int[] rr : r) {
                for (int rrr : rr) {
                    System.out.print(rrr);
                    System.out.print(" ");
                }
                System.out.print("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
