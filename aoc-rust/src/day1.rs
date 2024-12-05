use std::fs::File;
use std::io::{self, BufRead};

pub fn star1() -> std::io::Result<()> {
    println!("day1_star1");
    let file = File::open("input/day1.txt")?;
    let reader = io::BufReader::new(file);

    let mut vec1: Vec<i32> = Vec::new();
    let mut vec2: Vec<i32> = Vec::new();

    for line in reader.lines() {
        let next = line.unwrap();
        let splitted = next.split_whitespace().collect::<Vec<&str>>();
        vec1.push(splitted[0].parse::<i32>().unwrap());
        vec2.push(splitted[1].parse::<i32>().unwrap());
    }

    vec1.sort();
    vec2.sort();

    let mut sum = 0;
    vec1.iter().enumerate().for_each(|(i, x)| {
        sum += (x-vec2[i]).abs();
    });

    println!("Sum: {}", sum);

    Ok(())
}

pub fn star2() -> std::io::Result<()> {
    println!("day1_star2");
    let file = File::open("input/day1.txt")?;
    let reader = io::BufReader::new(file);

    let mut vec1: Vec<i32> = Vec::new();
    let mut vec2: Vec<i32> = Vec::new();

    for line in reader.lines() {
        let next = line.unwrap();
        let splitted = next.split_whitespace().collect::<Vec<&str>>();
        vec1.push(splitted[0].parse::<i32>().unwrap());
        vec2.push(splitted[1].parse::<i32>().unwrap());
    }

    let mut sum = 0;
    vec1.iter().for_each(|x| {
        let count = vec2.iter().filter(|y| *y == x).count();
        sum += (*x as usize) * count;
    }
    );

    println!("Sum: {}", sum);

    Ok(())
}
