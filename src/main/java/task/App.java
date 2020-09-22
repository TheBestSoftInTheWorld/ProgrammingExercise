package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
class App {
	public static void main(String[] args) {
		System.out.println("Set position data, excample: 4,4,2,2");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String position = null;
		try {
			position = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (position.trim().length() == 0) {
			System.out.println("result: [-1,-1]");
		} else {
			boolean is_array_correct = checkArray(position);
			System.out.println("Is " + position + " correct:" + is_array_correct);
			if (is_array_correct) {
				int[] p = convertToArray(position);

				boolean pCheck = checkPositionInput(p);

				System.out.println("Is " + position + " correct data:" + pCheck);

				System.out.println("Set direction data, excample: 1,4,1,3,2,3,2,4,1,0");

				BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
				String direction = null;
				try {
					direction = reader2.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (direction.trim().length() == 0) {
					System.out.println("result: [-1,-1]");
				} else {
					boolean is_array_correct2 = checkArray(direction);
					System.out.println("Is " + direction + " correct array:" + is_array_correct2);

					if (is_array_correct2) {

						int[] d = convertToArray(direction);
						if (d.length == 0) {
							System.out.println("result: [-1,-1]");
						} else {
							boolean is_correct_data = checkDirectionData(d);
							if (is_correct_data) {
								int[] result = game(p, d);
								System.out.println("result: [" + result[0] + "," + result[1] + "]");
							} else {
								System.out.println("result: [-1,-1]");
							}
						}
					} else {
						System.out.println("result: [-1,-1]");
					}

				}
			} else {
				System.out.println("result: [-1,-1]");
			}
		}
	}

	private static boolean checkArray(String array) {
		Pattern pattern = Pattern.compile("[\\d*,]*\\d*");
		return pattern.matcher(array).matches();
	}

	private static boolean checkDirectionData(int[] direction) {
		boolean contains = IntStream.of(direction).anyMatch(x -> x > 4);
		if (contains) {
			return false;
		} else if (IntStream.of(direction).anyMatch(x -> x < 0)) {
			return false;
		}
		return true;
	}

	private static int[] convertToArray(String input) {
		int[] arr = null;
		try {

			String[] fn = input.split(",");

			int size = fn.length;
			arr = new int[size];
			for (int i = 0; i < size; i++) {
				arr[i] = Integer.parseInt(fn[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}

	private static boolean checkPositionInput(int[] position) {

		boolean contains = IntStream.of(position).anyMatch(x -> x < 0);
		if (contains || position.length != 4 || (position[0] == 0 && position[1] == 0)) {
			System.out.println("bad input data");
			System.out.println("Array with size of table or start position cointans number less then 0: " + contains);
			System.out.println(
					"Size of array with with size of table or start position ( mandatrory = 4 ) : " + position.length);
			System.out.println("table size are x = " + position[0] + " and y = " + position[1]
					+ ". Mandatory - one or both bigger then 0. ");
			return false;
		}
		int xArray = position[0] - 1;
		int yArray = position[1] - 1;
		int xPlace = position[2];
		int yPlace = position[3];

		if (xArray < xPlace || yArray < yPlace) {
			System.out.println("bad input data");
			System.out.println(
					"If x array < x start place - bad input data. x array:" + xArray + " x start place: " + xPlace);
			System.out.println(
					"If x array < x start place - bad input data. y array:" + yArray + " y start place: " + yPlace);
			return false;
		}

		return true;
	}

	private static int[] game(int[] intputPosition, int[] intputDirection) {
		int[] output = { -1, -1 };

		int xArray = intputPosition[0] - 1;
		int yArray = intputPosition[1] - 1;
		int xPlace = intputPosition[2];
		int yPlace = intputPosition[3];
		int direct = 0;// 0 = north, 1 = east, 2 = south, 3 = west
		System.out.println("array size: [" + xArray + "," + yArray + "]");
		System.out.println("start: [" + xPlace + "," + yPlace + "]");
		for (int i = 0; i < intputDirection.length; i++) {
			System.out.println("i: " + i);
			System.out.println("intputDirection[i]: " + intputDirection[i]);
			if (intputDirection[i] == 0) {

				return new int[] { xPlace, yPlace };
			} else if (intputDirection[i] == 1) {
				System.out.println("forward");
				if (direct == 0) {
					yPlace = yPlace - 1;
					System.out.println("move to: [" + xPlace + "," + yPlace + "]");
					if (!checkArray(xArray, yArray, xPlace, yPlace)) {
						return new int[] { -1, -1 };
					}
				} else if (direct == 1) {

					xPlace = xPlace + 1;
					System.out.println("move to: [" + xPlace + "," + yPlace + "]");
					if (!checkArray(xArray, yArray, xPlace, yPlace)) {
						return new int[] { -1, -1 };
					}

				} else if (direct == 2) {
					yPlace = yPlace + 1;
					System.out.println("move to: [" + xPlace + "," + yPlace + "]");
					if (!checkArray(xArray, yArray, xPlace, yPlace)) {
						return new int[] { -1, -1 };
					}
				} else if (direct == 3) {
					xPlace = xPlace - 1;
					System.out.println("move to: [" + xPlace + "," + yPlace + "]");
					if (!checkArray(xArray, yArray, xPlace, yPlace)) {
						return new int[] { -1, -1 };
					}

				}
			} else if (intputDirection[i] == 2) {
				System.out.println("backwards");
				if (direct == 0) {
					yPlace = yPlace + 1;
					System.out.println("move to: [" + xPlace + "," + yPlace + "]");
					if (!checkArray(xArray, yArray, xPlace, yPlace)) {
						return new int[] { -1, -1 };
					}
				} else if (direct == 1) {
					xPlace = xPlace - 1;
					System.out.println("move to: [" + xPlace + "," + yPlace + "]");
					if (!checkArray(xArray, yArray, xPlace, yPlace)) {
						return new int[] { -1, -1 };
					}

				} else if (direct == 2) {
					yPlace = yPlace - 1;
					System.out.println("move to: [" + xPlace + "," + yPlace + "]");
					if (!checkArray(xArray, yArray, xPlace, yPlace)) {
						return new int[] { -1, -1 };
					}
				} else if (direct == 3) {
					xPlace = xPlace + 1;
					System.out.println("move to: [" + xPlace + "," + yPlace + "]");
					if (!checkArray(xArray, yArray, xPlace, yPlace)) {
						return new int[] { -1, -1 };
					}

				}
			} else if (intputDirection[i] == 3) {

				if (direct != 3) {
					direct++;
				} else {
					direct = 0;
				}
				System.out.println("direct 0 = north, 1 = east, 2 = south, 3 = west: " + direct);
			} else if (intputDirection[i] == 4) {
				if (direct != 0) {
					direct--;
				} else {
					direct = 3;
				}
				System.out.println("direct 0 = north, 1 = east, 2 = south, 3 = west: " + direct);
			}

		}

		return output;
	}

	private static boolean checkArray(int xArray, int yArray, int xPlace, int yPlace) {

		if (0 > xPlace || 0 > yPlace || xArray < xPlace || yArray < yPlace) {
			System.out.println("object falls off the table");
			return false;
		}

		return true;
	}

}
