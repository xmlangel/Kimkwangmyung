#!/usr/bin/python
# Copyright (C) 2013 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

from __future__ import print_function
from optparse import OptionParser
from os import path
import pipes

from sys import stderr
from util import check_output

def mvn(action):
  return ['mvn', '--file', path.join(ROOT, 'fake_pom_%s.xml' % action)]

def mvn(action):
  return ['mvn', '--file', path.join(ROOT, 'fake_pom_%s.xml' % action)]

opts = OptionParser()
opts.add_option('--repository', help='maven repository id')
opts.add_option('--url', help='maven repository url')
opts.add_option('-o')
opts.add_option('-a', help='action (valid actions are: install,deploy)')
opts.add_option('-v', help='gerrit version')
opts.add_option('-s', action='append', help='triplet of artifactId:type:path')

args, ctx = opts.parse_args()
if not args.v:
  print('version is empty', file=stderr)
  exit(1)

common = [
  '-DgroupId=com.google.gerrit',
  '-Dversion=%s' % args.v,
]

ROOT = path.abspath(__file__)
for _ in range(0, 3):
  ROOT = path.dirname(ROOT)

if 'install' == args.a:
  cmd = mvn(args.a) + ['install:install-file'] + common
elif 'deploy' == args.a:
  cmd = mvn(args.a) + [
    'deploy:deploy-file',
    '-DrepositoryId=%s' % args.repository,
    '-Durl=%s' % args.url,
  ] + common
else:
  print("unknown action -a %s" % args.a, file=stderr)
  exit(1)

for spec in args.s:
  artifact, packaging_type, src = spec.split(':')
  cmds = cmd + [
    '-DartifactId=%s' % artifact,
    '-Dpackaging=%s' % packaging_type,
    '-Dfile=%s' % src,
  ]
  try:
    check_output(cmds)
  except Exception as e:
    cmds_str = ' '.join(pipes.quote(c) for c in cmds)
    print("%s command failed: `%s`: %s" % (args.a, cmds_str, e), file=stderr)
    exit(1)

with open(args.o, 'w') as fd:
  if args.repository:
    print('Repository: %s' % args.repository, file=fd)
  if args.url:
    print('URL: %s' % args.url, file=fd)
  print('Version: %s' % args.v, file=fd)
